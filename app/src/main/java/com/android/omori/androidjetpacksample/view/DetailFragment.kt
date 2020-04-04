package com.android.omori.androidjetpacksample.view

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.palette.graphics.Palette

import com.android.omori.androidjetpacksample.R
import com.android.omori.androidjetpacksample.databinding.FragmentDetailBinding
import com.android.omori.androidjetpacksample.model.DogPalette
import com.android.omori.androidjetpacksample.util.getProgressDrawable
import com.android.omori.androidjetpacksample.util.loadImage
import com.android.omori.androidjetpacksample.viewmodel.DetailViewModel
import com.android.omori.androidjetpacksample.viewmodel.ListViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private var dogUuid = 0
    private lateinit var viewModel : DetailViewModel
    private lateinit var dataBinding : FragmentDetailBinding
    private var sendSmsStarted = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)

        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            dogUuid = DetailFragmentArgs.fromBundle(it).dogUuid
        }

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.fetch(dogUuid)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.dogLiveData.observe(this, Observer { dog ->
            dog?.let {
                dataBinding.dog = dog
//                text_view_dog_name_detail.text = dog.dogBreed
//                text_view_dog_purpose_detail.text = dog.bredFor
//                text_view_dog_temperament_detail.text = dog.temperament
//                text_view_dog_life_span_detail.text = dog.lifeSpan
//                context?.let { image_view_dog_detail.loadImage(dog.imageUrl, getProgressDrawable(it)) }

                it.imageUrl?.let {
                    setupBackgroundColor(it)
                }
            }
        })
    }

    private fun setupBackgroundColor(url : String) {
        Glide.with(this)
            .asBitmap()
            .load(url)
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) {}

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    Palette.from(resource)
                        .generate { palette ->
                            val intColor = palette?.vibrantSwatch?.rgb ?: 0
                            val myPalette = DogPalette(intColor)
                            dataBinding.palette = myPalette
                        }
                }
            })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.detail_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.action_send_sms -> {
                sendSmsStarted = true
                (activity as MainActivity).checkSmsPermission()
            }

            R.id.action_share -> {

            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun onPermissionResult(permissionGranted : Boolean) {

    }

}

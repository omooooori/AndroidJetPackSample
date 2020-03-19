package com.android.omori.androidjetpacksample.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

import com.android.omori.androidjetpacksample.R
import com.android.omori.androidjetpacksample.util.getProgressDrawable
import com.android.omori.androidjetpacksample.util.loadImage
import com.android.omori.androidjetpacksample.viewmodel.DetailViewModel
import com.android.omori.androidjetpacksample.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private var dogUuid = 0
    private lateinit var viewModel : DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
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
                text_view_dog_name_detail.text = dog.dogBreed
                text_view_dog_purpose_detail.text = dog.bredFor
                text_view_dog_temperament_detail.text = dog.temperament
                text_view_dog_life_span_detail.text = dog.lifeSpan
                context?.let { image_view_dog_detail.loadImage(dog.imageUrl, getProgressDrawable(it)) }
            }
        })
    }

}

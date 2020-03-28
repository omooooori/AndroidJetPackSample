package com.android.omori.androidjetpacksample.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.android.omori.androidjetpacksample.R
import com.android.omori.androidjetpacksample.databinding.ItemDogBinding
import com.android.omori.androidjetpacksample.model.DogBreed
import com.android.omori.androidjetpacksample.util.getProgressDrawable
import com.android.omori.androidjetpacksample.util.loadImage
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.item_dog.view.*

class DogListAdapter(val dogList: ArrayList<DogBreed>) : RecyclerView.Adapter<DogListAdapter.DogViewHolder>() {

    fun updateDogList(newDogList: List<DogBreed>) {
        dogList.clear()
        dogList.addAll(newDogList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.item_dog, parent, false)
        var view = DataBindingUtil.inflate<ItemDogBinding>(inflater, R.layout.item_dog, parent, false)
        return DogViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dogList.size
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.view.dog = dogList[position]
//        holder.view.text_view_dog_name.text = dogList[position].dogBreed
//        holder.view.text_view_dog_life_span.text = dogList[position].lifeSpan
//        holder.view.setOnClickListener {
//            val action = ListFragmentDirections.actionDetailFragment()
//            action.dogUuid = dogList[position].uuid
//            Navigation.findNavController(it).navigate(action)
//        }
//        holder.view.image_view_dog.loadImage(
//            dogList[position].imageUrl,
//            getProgressDrawable(holder.view.image_view_dog.context)
//        )
    }

    class DogViewHolder(var view: ItemDogBinding) : RecyclerView.ViewHolder(view.root)

}
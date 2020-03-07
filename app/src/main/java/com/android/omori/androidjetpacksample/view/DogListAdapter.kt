package com.android.omori.androidjetpacksample.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.omori.androidjetpacksample.R
import com.android.omori.androidjetpacksample.model.DogBreed
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
        val view = inflater.inflate(R.layout.item_dog, parent, false)
        return DogViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dogList.size
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.view.text_view_dog_name.text = dogList[position].dogBreed
        holder.view.text_view_dog_life_span.text = dogList[position].lifeSpan
    }

    class DogViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }

}
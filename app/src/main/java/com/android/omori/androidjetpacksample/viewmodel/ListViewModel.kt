package com.android.omori.androidjetpacksample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.omori.androidjetpacksample.model.DogBreed

class ListViewModel : ViewModel() {

    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        // This hard coding is just temporary. I will implement to get data using API.
        val dog1 = DogBreed(
            "1",
            "Corgi",
            "15 years",
            "breedGroup",
            "bredFor",
            "temperament",
            ""
        )

        val dog2 = DogBreed(
            "2",
            "Labrador",
            "10 years",
            "breedGroup",
            "bredFor",
            "temperament",
            ""
        )

        val dog3 = DogBreed(
            "3",
            "Rottweiler",
            "20 years",
            "breedGroup",
            "bredFor",
            "temperament",
            ""
        )

        val dogList : ArrayList<DogBreed> = arrayListOf<DogBreed>(dog1, dog2, dog3)
        dogs.value = dogList
        dogsLoadError.value = false
        loading.value = false
    }

}
package com.android.omori.androidjetpacksample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.omori.androidjetpacksample.model.DogBreed

class DetailViewModel : ViewModel() {

    val dogLiveData = MutableLiveData<DogBreed>()

    fun fetch() {
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

        dogLiveData.value = dog1
    }

}
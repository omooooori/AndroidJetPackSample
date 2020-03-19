package com.android.omori.androidjetpacksample.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.omori.androidjetpacksample.model.DogBreed
import com.android.omori.androidjetpacksample.model.DogDatabase
import com.android.omori.androidjetpacksample.view.BaseViewModel
import kotlinx.coroutines.launch

class DetailViewModel(application : Application) : BaseViewModel(application) {

    val dogLiveData = MutableLiveData<DogBreed>()

    fun fetch(dogId : Int) {
//        This hard coding is just temporary. I will implement to get data using API.
//        val dog1 = DogBreed(
//            "1",
//            "Corgi",
//            "15 years",
//            "breedGroup",
//            "bredFor",
//            "temperament",
//            ""
//        )
//
//        dogLiveData.value = dog1

        fetchFromDatabase(dogId)
    }

    private fun fetchFromDatabase(dogId : Int) {
        launch {
            dogLiveData.value = DogDatabase(getApplication()).dogDao().getDog(dogId)
            Toast.makeText(getApplication(), "Dogs retrieved from database for Detail Screen", Toast.LENGTH_LONG).show()
        }
    }

}
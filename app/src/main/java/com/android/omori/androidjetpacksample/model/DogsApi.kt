package com.android.omori.androidjetpacksample.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST

interface DogsApi {
    @GET("DevTides/DogsApi/master/dogs.json")
    fun getDogs() : Single<List<DogBreed>>
}
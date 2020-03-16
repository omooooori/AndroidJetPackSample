package com.android.omori.androidjetpacksample.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DogDao {
    @Insert
    suspend fun insertAll(vararg dogs : DogBreed) : List<Long>  // get uuid (primary key)

    @Query(value = "SELECT * FROM dogbreed")
    suspend fun getAllDogs() : List<DogBreed>

    @Query(value = "SELECT * FROM dogbreed WHERE uuid = :dogId")
    suspend fun getDog(dogId : Int) : DogBreed

    @Query(value = "DELETE FROM dogbreed")
    suspend fun deleteAllDogs()
}
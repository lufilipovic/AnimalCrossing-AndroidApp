package com.example.animal_crossing.data.api

import com.example.animal_crossing.data.api.model.Fish
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://acnhapi.com/v1/"

interface APIService {
    @GET("fish")
    suspend fun getAllFish(): List<Fish>

    companion object {
        var apiService: APIService? = null
        fun getInstance(): APIService {
            if(apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(APIService::class.java)
            }
            return apiService!!
        }
    }
}
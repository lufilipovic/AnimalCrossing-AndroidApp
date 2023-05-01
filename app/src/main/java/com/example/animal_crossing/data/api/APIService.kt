package com.example.animal_crossing.data.api

import com.example.animal_crossing.data.api.model.BugItem
import com.example.animal_crossing.data.api.model.FishItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

const val BASE_URL = "https://api.nookipedia.com/nh/"
const val API_KEY = "8644fd1d-34b7-4c7e-9438-8e7d774e8d76"

interface APIService {
    @Headers("X-API-KEY: $API_KEY")
    @GET("fish")
    suspend fun getAllFish(): List<FishItem>

    @Headers("X-API-KEY: $API_KEY")
    @GET("bugs")
    suspend fun getAllBugs(): List<BugItem>

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
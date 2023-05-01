package com.example.animal_crossing.data.api

import com.example.animal_crossing.data.api.model.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

const val BASE_URL = "https://api.nookipedia.com/"
const val API_KEY = "8644fd1d-34b7-4c7e-9438-8e7d774e8d76"

interface APIService {
    @Headers("X-API-KEY: $API_KEY")
    @GET("nh/fish")
    suspend fun getAllFish(): List<FishItem>

    @Headers("X-API-KEY: $API_KEY")
    @GET("nh/bugs")
    suspend fun getAllBugs(): List<BugItem>

    @Headers("X-API-KEY: $API_KEY")
    @GET("nh/sea")
    suspend fun getAllSeaCreatures(): List<SeaCreatureItem>

    @Headers("X-API-KEY: $API_KEY")
    @GET("villagers")
    suspend fun getAllVillagers(): List<VillagerItem>

    @Headers("X-API-KEY: $API_KEY")
    @GET("nh/fossils/individuals")
    suspend fun getAllFossils(): List<FossilItem>

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
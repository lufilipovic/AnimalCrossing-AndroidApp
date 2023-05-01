package com.example.animal_crossing.data.api.model


import com.google.gson.annotations.SerializedName

data class AvailabilityArray(
    @SerializedName("months")
    val months: String,
    @SerializedName("time")
    val time: String
)
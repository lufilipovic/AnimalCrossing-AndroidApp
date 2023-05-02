package com.example.animal_crossing.data.api.model


import com.google.gson.annotations.SerializedName

data class South(
    @SerializedName("availability_array")
    val availabilityArray: List<AvailabilityArray>,
    @SerializedName("months")
    val months: String,
    @SerializedName("months_array")
    val monthsArray: List<Int>,
    @SerializedName("times_by_month")
    val timesByMonth: TimesByMonth
)
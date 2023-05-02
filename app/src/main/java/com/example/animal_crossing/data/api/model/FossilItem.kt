package com.example.animal_crossing.data.api.model


import com.google.gson.annotations.SerializedName

data class FossilItem(
    @SerializedName("colors")
    val colors: List<String>,
    @SerializedName("fossil_group")
    val fossilGroup: String,
    @SerializedName("hha_base")
    val hhaBase: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("interactable")
    val interactable: Boolean,
    @SerializedName("length")
    val length: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("sell")
    val sell: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)
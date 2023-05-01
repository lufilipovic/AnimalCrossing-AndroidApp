package com.example.animal_crossing.data.api.model


import com.google.gson.annotations.SerializedName

data class Fish(
    @SerializedName("availability")
    val availability: Availability,
    @SerializedName("catch-phrase")
    val catchPhrase: String,
    @SerializedName("file-name")
    val fileName: String,
    @SerializedName("icon_uri")
    val iconUri: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_uri")
    val imageUri: String,
    @SerializedName("museum-phrase")
    val museumPhrase: String,
    @SerializedName("name")
    val name: Name,
    @SerializedName("price")
    val price: Int,
    @SerializedName("price-cj")
    val priceCj: Int,
    @SerializedName("shadow")
    val shadow: String
)
package com.example.animal_crossing.data.api.model


import com.google.gson.annotations.SerializedName

data class SeaCreatureItem(
    @SerializedName("catchphrases")
    val catchphrases: List<String>,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("north")
    val north: North,
    @SerializedName("number")
    val number: Int,
    @SerializedName("rarity")
    val rarity: String,
    @SerializedName("render_url")
    val renderUrl: String,
    @SerializedName("sell_nook")
    val sellNook: Int,
    @SerializedName("shadow_movement")
    val shadowMovement: String,
    @SerializedName("shadow_size")
    val shadowSize: String,
    @SerializedName("south")
    val south: South,
    @SerializedName("tank_length")
    val tankLength: Int,
    @SerializedName("tank_width")
    val tankWidth: Int,
    @SerializedName("total_catch")
    val totalCatch: Int,
    @SerializedName("url")
    val url: String
)
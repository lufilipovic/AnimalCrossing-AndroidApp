package com.example.animal_crossing.data.api.model


import com.google.gson.annotations.SerializedName

data class NhDetails(
    @SerializedName("catchphrase")
    val catchphrase: String,
    @SerializedName("clothing")
    val clothing: String,
    @SerializedName("clothing_variation")
    val clothingVariation: String,
    @SerializedName("fav_colors")
    val favColors: List<String>,
    @SerializedName("fav_styles")
    val favStyles: List<String>,
    @SerializedName("hobby")
    val hobby: String,
    @SerializedName("house_exterior_url")
    val houseExteriorUrl: String,
    @SerializedName("house_flooring")
    val houseFlooring: String,
    @SerializedName("house_interior_url")
    val houseInteriorUrl: String,
    @SerializedName("house_music")
    val houseMusic: String,
    @SerializedName("house_music_note")
    val houseMusicNote: String,
    @SerializedName("house_wallpaper")
    val houseWallpaper: String,
    @SerializedName("icon_url")
    val iconUrl: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("photo_url")
    val photoUrl: String,
    @SerializedName("quote")
    val quote: String,
    @SerializedName("sub-personality")
    val subPersonality: String
)
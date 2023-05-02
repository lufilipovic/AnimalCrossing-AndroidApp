package com.example.animal_crossing.data.api.model


import com.google.gson.annotations.SerializedName

data class VillagerItem(
    @SerializedName("alt_name")
    val altName: String,
    @SerializedName("appearances")
    val appearances: List<String>,
    @SerializedName("birthday_day")
    val birthdayDay: String,
    @SerializedName("birthday_month")
    val birthdayMonth: String,
    @SerializedName("clothing")
    val clothing: String,
    @SerializedName("debut")
    val debut: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("islander")
    val islander: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("nh_details")
    val nhDetails: NhDetails,
    @SerializedName("personality")
    val personality: String,
    @SerializedName("phrase")
    val phrase: String,
    @SerializedName("prev_phrases")
    val prevPhrases: List<String>,
    @SerializedName("quote")
    val quote: String,
    @SerializedName("sign")
    val sign: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("text_color")
    val textColor: String,
    @SerializedName("title_color")
    val titleColor: String,
    @SerializedName("url")
    val url: String
)
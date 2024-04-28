package com.sean.taipeizoo.model

import com.google.gson.annotations.SerializedName

data class Animal(
    @SerializedName(value = "_id")
    val id: Int,
    @SerializedName(value = "a_name_ch")
    val nameCh: String,
    @SerializedName(value = "a_name_en")
    val nameEn: String,
    @SerializedName(value = "a_alsoknown")
    val alsoKnown: String,
    @SerializedName(value = "a_distribution")
    val distribution: String,
    @SerializedName(value = "a_habitat")
    val habitat: String,
    @SerializedName(value = "a_feature")
    val feature: String,
    @SerializedName(value = "a_behavior")
    val behavior: String,
    @SerializedName(value = "a_update")
    val update: String,
    @SerializedName(value = "a_pic01_url")
    val imageUrl: String
)
package com.sean.taipeizoo.model

import com.google.gson.annotations.SerializedName

data class Area(
    @SerializedName(value = "_id")
    val id: Int,
    @SerializedName(value = "e_name")
    val name: String,
    @SerializedName(value = "e_info")
    val info: String,
    @SerializedName(value = "e_memo")
    val memo: String,
    @SerializedName(value = "e_pic_url")
    val imageUrl: String
)
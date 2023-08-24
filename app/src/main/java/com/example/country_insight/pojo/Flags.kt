package com.example.country_insight.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Flags(
    @SerializedName("png")
    @Expose
    val png: String,
)

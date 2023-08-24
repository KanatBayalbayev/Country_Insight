package com.example.country_insight.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class JsonObject(
    @SerializedName("name")
    @Expose
    val name: Name,

    @SerializedName("capital")
    @Expose
    val capital: List<String>,

    @SerializedName("subregion")
    @Expose
    val subregion: String,

    @SerializedName("population")
    @Expose
    val population: Long,

    @SerializedName("flags")
    @Expose
    val flags: Flags,
)

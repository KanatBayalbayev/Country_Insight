package com.example.country_insight.api

import androidx.lifecycle.LiveData
import com.example.country_insight.pojo.JsonObject
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("name/{name}")
    fun getCountryInfo(@Path("name") name: String): Single<List<JsonObject>>
}
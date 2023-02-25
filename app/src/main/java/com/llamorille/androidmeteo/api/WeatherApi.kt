package com.llamorille.androidmeteo.api

import com.llamorille.androidmeteo.model.SearchResponse
import com.llamorille.androidmeteo.model.WeatherMain
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherApi {
    @Headers("Accept: application/json")
    @GET("current.json")
    suspend fun getWeatherData(@Query("q") city:String, @Query("key") apiKey:String, @Query("aqi") aqi: String): WeatherMain

    @Headers("Accept: application/json")
    @GET("search.json")
    suspend fun searchCity(@Query("q") city: String, @Query("key") apiKey: String): List<SearchResponse>
}
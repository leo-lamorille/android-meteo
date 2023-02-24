package com.llamorille.androidmeteo.api

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherApi {
    @Headers("Accept: application/json")
    @GET("current.json")
    suspend fun getWeatherData(@Query("q") city:String, @Query("key") apiKey:String, @Query("aqi") aqi: String): WeatherMain
}
package com.llamorille.androidmeteo.api

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiService {
    private val BASE_URL = "https://api.weatherapi.com/v1/"
    private val API_KEY_WEATHER = "e4d402542cd14ddc83195806232402 "

    //val BASE_IMAGE_URL = "https://openweathermap.org/img/wn/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create().asLenient())
        .build()

    private val service: WeatherApi = retrofit.create(WeatherApi::class.java)

    suspend fun findWeatherByCity(city: String): WeatherMain {
        Log.d("TEST", city);
        val test = service.getWeatherData(city, API_KEY_WEATHER, "no");
        Log.d("Test 2", test.toString())
        return test;
    }
}

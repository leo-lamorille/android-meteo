package com.llamorille.androidmeteo.api

import com.llamorille.androidmeteo.model.SearchResponse
import com.llamorille.androidmeteo.model.WeatherMain
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiService {
    private const val BASE_URL = "https://api.weatherapi.com/v1/";
    private const val API_KEY_WEATHER = "e4d402542cd14ddc83195806232402";

    //val BASE_IMAGE_URL = "https://openweathermap.org/img/wn/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create().asLenient())
        .build()

    private val service: WeatherApi = retrofit.create(WeatherApi::class.java)

    suspend fun findWeatherByCity(city: String): WeatherMain {
        return service.getWeatherData(city, API_KEY_WEATHER, "no")
    }

    suspend fun searchCity(city: String): List<SearchResponse> {
        return service.searchCity(city, API_KEY_WEATHER)
    }
}

package com.llamorille.androidmeteo.model

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class ForecastDay(
    val date: String?,
    val hour: List<HourWeather>?,
): Serializable
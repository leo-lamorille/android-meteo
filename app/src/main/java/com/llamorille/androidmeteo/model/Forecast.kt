package com.llamorille.androidmeteo.model

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Forecast(
    val forecastday: List<ForecastDay>?
): Serializable

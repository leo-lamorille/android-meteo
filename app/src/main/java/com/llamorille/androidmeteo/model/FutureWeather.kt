package com.llamorille.androidmeteo.model

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class FutureWeather(
    val forecast: Forecast?
): Serializable

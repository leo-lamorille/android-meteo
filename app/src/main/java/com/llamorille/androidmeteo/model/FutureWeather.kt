package com.llamorille.androidmeteo.model

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class FutureWeather(
    val location: Location?,
    val forecast: Forecast?
): Serializable

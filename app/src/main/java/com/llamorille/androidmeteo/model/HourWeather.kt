package com.llamorille.androidmeteo.model

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class HourWeather(
    val time: String?,
    val temp_c: Float?,
    val is_day: Int,
    val condition: Condition?,
): Serializable
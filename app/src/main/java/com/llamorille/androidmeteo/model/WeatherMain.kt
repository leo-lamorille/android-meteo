package com.llamorille.androidmeteo.model

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class WeatherMain (
  val location: Location?= null,
  val current: Current?= null
): Serializable
package com.llamorille.androidmeteo.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherMain (
  val location: Location?= null,
  val current: Current?= null
)
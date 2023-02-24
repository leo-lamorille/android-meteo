package com.llamorille.androidmeteo.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Current (
  val temp_c: Double?,
  val condition: Condition?,
  val wind_kph: Float?,
  val wind_dir: String?,
  val humidity: Float,
)
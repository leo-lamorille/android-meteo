package com.llamorille.androidmeteo.model

import com.squareup.moshi.JsonClass
import java.io.Serializable


@JsonClass(generateAdapter = true)
data class Current (
  val temp_c: Double?,
  val condition: Condition?,
  val wind_kph: Float?,
  val wind_dir: String?,
  val humidity: Float,
): Serializable
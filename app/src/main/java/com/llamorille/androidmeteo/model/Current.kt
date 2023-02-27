package com.llamorille.androidmeteo.model

import com.squareup.moshi.JsonClass
import java.io.Serializable


@JsonClass(generateAdapter = true)
data class Current (
  val avgtemp_c: Double?,
  val condition: Condition?,
  val maxwind_kph: Float?,
  val avghumidity: Float,
): Serializable
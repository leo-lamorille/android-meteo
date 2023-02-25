package com.llamorille.androidmeteo.model

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Condition(
    val text: String?,
    val icon: String?,
    val code: Int?
): Serializable

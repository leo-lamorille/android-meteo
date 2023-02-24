package com.llamorille.androidmeteo.api

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Location(
    val name: String?,
    val country: String?
) : Serializable

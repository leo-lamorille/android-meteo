package com.llamorille.androidmeteo.model

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Location(
    val name: String?,
    val country: String?,
    val localtime: String?
) : Serializable

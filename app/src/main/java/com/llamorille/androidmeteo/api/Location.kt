package com.llamorille.androidmeteo.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location(
    val name: String?,
    val country: String?
)

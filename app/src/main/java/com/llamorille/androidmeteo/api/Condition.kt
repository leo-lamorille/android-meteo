package com.llamorille.androidmeteo.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Condition(
    val text: String?,
    val icon: String?,
    val code: Int?
)

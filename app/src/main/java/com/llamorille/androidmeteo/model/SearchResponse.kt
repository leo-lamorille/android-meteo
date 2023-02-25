package com.llamorille.androidmeteo.model

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class SearchResponse(
    val id: Int?,
    val name: String?,
    val country: String?
): Serializable

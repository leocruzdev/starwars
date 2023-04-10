package com.dacruz.home.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Category(
    @Json(name = "people") val people: String,
    @Json(name = "planets") val planets: String,
    @Json(name = "films") val films: String,
    @Json(name = "species") val species: String,
    @Json(name = "vehicles") val vehicles: String,
    @Json(name = "starships") val starships: String
)
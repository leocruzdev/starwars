package com.dacruz.character.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterListDTO(
    @Json(name = "count")
    val count: Int,
    @Json(name = "next")
    val next: String?,
    @Json(name = "previous")
    val previous: String?,
    @Json(name = "results")
    val characters: List<CharacterDTO>
)

@JsonClass(generateAdapter = true)
data class CharacterDTO(
    @Json(name = "name")
    val name: String,
    @Json(name = "height")
    val height: String,
    @Json(name = "mass")
    val mass: String,
    @Json(name = "hair_color")
    val hairColor: String,
    @Json(name = "skin_color")
    val skinColor: String,
    @Json(name = "eye_color")
    val eyeColor: String,
    @Json(name = "birth_year")
    val birthYear: String,
    @Json(name = "gender")
    val gender: String,
    @Json(name = "homeworld")
    val homeWorldUrl: String,
    @Json(name = "films")
    val filmsUrls: List<String>,
    @Json(name = "species")
    val speciesUrls: List<String>,
    @Json(name = "vehicles")
    val vehiclesUrls: List<String>,
    @Json(name = "starships")
    val starshipsUrls: List<String>,
    @Json(name = "created")
    val created: String,
    @Json(name = "edited")
    val edited: String,
    @Json(name = "url")
    val url: String
)
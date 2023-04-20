package com.dacruz.character.presentation.model

import androidx.compose.ui.graphics.ImageBitmap

data class CharacterUiModel(
    val name: String,
    val birthYear: String,
    val gender: String,
    val height: String,
    val mass: String,
    val hairColor: String,
    val skinColor: String,
    val eyeColor: String,
    val url: String,
    val isFavorite: Boolean = false,
    val characterImage: String = "",
    var favoriteImage: ImageBitmap? = null
)
package com.dacruz.favorites.presentation.mapper

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.dacruz.favorites.presentation.model.FavoriteUiModel
import com.dacruz.favorites.domain.model.FavoriteCharacter as CharacterDomain

class FavoriteCharacterViewMapper {

    fun toUiModel(domainCharacter: CharacterDomain): FavoriteUiModel {
        return FavoriteUiModel(
            name = domainCharacter.name,
            height = domainCharacter.height,
            mass = domainCharacter.mass,
            hairColor = domainCharacter.hairColor,
            skinColor = domainCharacter.skinColor,
            eyeColor = domainCharacter.eyeColor,
            birthYear = domainCharacter.birthYear,
            gender = domainCharacter.gender,
            url = domainCharacter.url,
            favoriteImage = domainCharacter.favoriteImage?.toImageBitmap()
        )
    }

    fun ByteArray.toImageBitmap(): ImageBitmap? {
        val androidBitmap = BitmapFactory.decodeByteArray(this, 0, size)
        return androidBitmap?.asImageBitmap()
    }
}
package com.dacruz.character.presentation.mapper

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.dacruz.character.data.mapper.FavoriteCharacterDataMapper
import com.dacruz.character.presentation.model.CharacterUiModel
import com.dacruz.database.model.FavoriteCharacterEntity
import java.io.ByteArrayOutputStream
import com.dacruz.character.domain.model.Character as CharacterDomain

class FavoriteCharacteViewMapper(
    private val characterDataMapper: FavoriteCharacterDataMapper,
    private val characterViewMapper: CharacterViewMapper
) {

    fun toDomain(favoriteCharacters: List<FavoriteCharacterEntity>): List<CharacterDomain> {
        return favoriteCharacters.map {
            characterDataMapper.toDomain(it, true)
        }
    }

    fun toDomain(uiCharacter: CharacterUiModel): CharacterDomain {
        return CharacterDomain(
            name = uiCharacter.name,
            height = uiCharacter.height,
            mass = uiCharacter.mass,
            hairColor = uiCharacter.hairColor,
            skinColor = uiCharacter.skinColor,
            eyeColor = uiCharacter.eyeColor,
            birthYear = uiCharacter.birthYear,
            gender = uiCharacter.gender,
            url = uiCharacter.url,
            favoriteImage = uiCharacter.favoriteImage?.toByteArray()
        )
    }


    fun toUiModel(favoriteCharacter: FavoriteCharacterEntity): CharacterUiModel {
        val characterDomain = characterDataMapper.toDomain(favoriteCharacter, true)
        val uiModel = characterViewMapper.toView(characterDomain)
        return uiModel.copy(
            isFavorite = true,
            favoriteImage = favoriteCharacter.imageByteArray?.toImageBitmap()
        )
    }

    fun toUiModel(characterDomain: CharacterDomain): CharacterUiModel {
        val characterUiModel = characterViewMapper.toView(characterDomain)
        return characterUiModel.copy(isFavorite = true)
    }

    fun toUiModel(characterDomainList: List<CharacterDomain>): List<CharacterUiModel> {
        return characterDomainList.map { toUiModel(it) }
    }

    private fun ImageBitmap.toByteArray(): ByteArray {
        val stream = ByteArrayOutputStream()
        val androidBitmap = this.asAndroidBitmap()
        androidBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

    fun ByteArray.toImageBitmap(): ImageBitmap? {
        val androidBitmap = BitmapFactory.decodeByteArray(this, 0, size)
        return androidBitmap?.asImageBitmap()
    }
}
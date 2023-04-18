package com.dacruz.favorites.data.mapper

import com.dacruz.database.model.FavoriteCharacterEntity
import com.dacruz.favorites.domain.model.FavoriteCharacter as CharacterDomain

class FavoriteCharacterDataMapper {

    fun toFavoriteEntity(character: CharacterDomain): FavoriteCharacterEntity =
        FavoriteCharacterEntity(
            name = character.name,
            birthYear = character.birthYear,
            gender = character.gender,
            height = character.height,
            mass = character.mass,
            hairColor = character.hairColor,
            skinColor = character.skinColor,
            eyeColor = character.eyeColor,
            url = character.url,
            imageByteArray = character.favoriteImage,
        )

    fun toDomain(favoriteEntity: FavoriteCharacterEntity): CharacterDomain {
        return CharacterDomain(
            name = favoriteEntity.name,
            birthYear = favoriteEntity.birthYear,
            gender = favoriteEntity.gender,
            height = favoriteEntity.height,
            mass = favoriteEntity.mass,
            hairColor = favoriteEntity.hairColor,
            skinColor = favoriteEntity.skinColor,
            eyeColor = favoriteEntity.eyeColor,
            url = favoriteEntity.url,
            isFavorite = true,
            favoriteImage = favoriteEntity.imageByteArray
        )
    }
}
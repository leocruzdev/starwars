package com.dacruz.character.data.local

import kotlinx.coroutines.flow.Flow
import com.dacruz.character.domain.model.Character as CharacterDomain

interface LocalDataSource {
    suspend fun addToFavorites(character: CharacterDomain)

    suspend fun removeFromFavorites(url: String)

    suspend fun isCharacterFavorite(url: String): Flow<Boolean>

    suspend fun getImageByUrl(url: String): Flow<ByteArray?>

}
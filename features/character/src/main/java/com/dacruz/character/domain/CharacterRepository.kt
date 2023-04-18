package com.dacruz.character.domain

import kotlinx.coroutines.flow.Flow
import com.dacruz.character.domain.model.Character as CharacterDomain

interface CharacterRepository {

    suspend fun getCharacters(page: Int): Flow<List<CharacterDomain>>

    suspend fun addToFavorites(character: CharacterDomain)

    suspend fun removeFromFavorites(url: String)

    suspend fun isCharacterFavorite(url: String): Flow<Boolean>

    suspend fun getImageByUrl(url: String): Flow<ByteArray?>
}
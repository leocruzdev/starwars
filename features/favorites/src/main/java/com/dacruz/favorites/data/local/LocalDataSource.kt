package com.dacruz.favorites.data.local

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import com.dacruz.favorites.domain.model.FavoriteCharacter as CharacterDomain

interface LocalDataSource {

    fun getFavoriteCharactersPaged(): Flow<PagingData<CharacterDomain>>

    suspend fun addToFavorites(character: CharacterDomain)

    suspend fun removeFromFavorites(url: String)

    suspend fun getImageByUrl(url: String): Flow<ByteArray?>
}
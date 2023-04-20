package com.dacruz.favorites.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import com.dacruz.favorites.domain.model.FavoriteCharacter as CharacterDomain

interface FavoriteRepository {

    fun getFavoriteCharacters(): Flow<PagingData<CharacterDomain>>

    suspend fun removeFromFavorites(url: String)

    suspend fun getImageByUrl(url: String): Flow<ByteArray?>
}
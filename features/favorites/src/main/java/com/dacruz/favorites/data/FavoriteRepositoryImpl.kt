package com.dacruz.favorites.data

import androidx.paging.PagingData
import com.dacruz.favorites.data.local.LocalDataSource
import com.dacruz.favorites.domain.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import com.dacruz.favorites.domain.model.FavoriteCharacter as CharacterDomain

class FavoriteRepositoryImpl(
    private val localDataSource: LocalDataSource,
) : FavoriteRepository {

    override fun getFavoriteCharacters(): Flow<PagingData<CharacterDomain>> {
        return localDataSource.getFavoriteCharactersPaged()
    }

    override suspend fun removeFromFavorites(url: String) {
        localDataSource.removeFromFavorites(url)
    }

    override suspend fun getImageByUrl(url: String): Flow<ByteArray?> {
        return localDataSource.getImageByUrl(url)
    }

}
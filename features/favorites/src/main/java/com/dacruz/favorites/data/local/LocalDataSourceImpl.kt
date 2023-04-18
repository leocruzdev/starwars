package com.dacruz.favorites.data.local

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.dacruz.database.FavoriteCharacterDao
import com.dacruz.favorites.data.mapper.FavoriteCharacterDataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import com.dacruz.favorites.domain.model.FavoriteCharacter as CharacterDomain

class LocalDataSourceImpl(
    private val favoriteCharacterDao: FavoriteCharacterDao,
    private val mapper: FavoriteCharacterDataMapper,
) : LocalDataSource {

    override fun getFavoriteCharactersPaged(): Flow<PagingData<CharacterDomain>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CharacterPagingSource(favoriteCharacterDao) }
        ).flow.map { pagingData ->
            pagingData.map { entity ->
                mapper.toDomain(entity)
            }
        }
    }

    override suspend fun addToFavorites(character: CharacterDomain) {
        return withContext(Dispatchers.IO) {
            favoriteCharacterDao.insertFavoriteCharacter(mapper.toFavoriteEntity(character))
        }
    }

    override suspend fun removeFromFavorites(url: String) {
        return withContext(Dispatchers.IO) {
            favoriteCharacterDao.deleteFavoriteCharacterByUrl(url)
        }
    }

    override suspend fun getImageByUrl(url: String): Flow<ByteArray?> {
        return favoriteCharacterDao.getCharacterImageByUrl(url)
    }

    companion object {
        const val PAGE_SIZE = 2
    }
}
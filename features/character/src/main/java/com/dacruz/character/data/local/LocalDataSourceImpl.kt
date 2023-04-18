package com.dacruz.character.data.local

import com.dacruz.character.data.mapper.FavoriteCharacterDataMapper
import com.dacruz.database.FavoriteCharacterDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import com.dacruz.character.domain.model.Character as CharacterDomain

class LocalDataSourceImpl(
    private val favoriteCharacterDao: FavoriteCharacterDao,
    private val mapper: FavoriteCharacterDataMapper
) : LocalDataSource {

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

    override suspend fun isCharacterFavorite(url: String): Flow<Boolean> {
        return favoriteCharacterDao.isCharacterFavorite(url)
    }

    override suspend fun getImageByUrl(url: String): Flow<ByteArray?> {
        return favoriteCharacterDao.getCharacterImageByUrl(url)
    }
}
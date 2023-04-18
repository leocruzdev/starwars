package com.dacruz.character.data

import com.dacruz.character.data.local.LocalDataSource
import com.dacruz.character.data.remote.RemoteDataSource
import com.dacruz.character.domain.CharacterRepository
import kotlinx.coroutines.flow.Flow
import com.dacruz.character.domain.model.Character as CharacterDomain

class CharacterRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : CharacterRepository {

    override suspend fun getCharacters(page: Int): Flow<List<CharacterDomain>> {
        return remoteDataSource.getCharacters(page)
    }

    override suspend fun addToFavorites(character: CharacterDomain) {
        localDataSource.addToFavorites(character)
    }

    override suspend fun removeFromFavorites(url: String) {
        localDataSource.removeFromFavorites(url)
    }

    override suspend fun isCharacterFavorite(url: String): Flow<Boolean> {
        return localDataSource.isCharacterFavorite(url)
    }

    override suspend fun getImageByUrl(url: String): Flow<ByteArray?> {
        return localDataSource.getImageByUrl(url)
    }
}
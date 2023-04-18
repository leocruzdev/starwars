package com.dacruz.character.data.remote

import kotlinx.coroutines.flow.Flow
import com.dacruz.character.domain.model.Character as CharacterDomain

interface RemoteDataSource {
    suspend fun getCharacters(page: Int): Flow<List<CharacterDomain>>
}
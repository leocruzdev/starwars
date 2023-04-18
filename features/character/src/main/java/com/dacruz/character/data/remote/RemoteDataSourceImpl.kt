package com.dacruz.character.data.remote

import com.dacruz.character.data.mapper.CharacterDataMapper
import com.dacruz.character.data.service.CharacterService
import com.dacruz.networking.executeRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.dacruz.character.domain.model.Character as CharacterDomain

class RemoteDataSourceImpl(
    private val service: CharacterService,
    private val mapper: CharacterDataMapper
) : RemoteDataSource {

    override suspend fun getCharacters(page: Int): Flow<List<CharacterDomain>> {
        val url = "$CHARACTERS_ENDPOINT?page=$page"

        return flow {
            executeRequest {
                val data = service.getCharacters(url)
                val mappedData = mapper.toDomain(data)
                emit(mappedData)
            }
        }
    }

    companion object {
        const val CHARACTERS_ENDPOINT = "api/people/"
    }
}
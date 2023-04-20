package com.dacruz.character.domain.usecase

import com.dacruz.character.domain.CharacterRepository
import kotlinx.coroutines.flow.Flow
import com.dacruz.character.domain.model.Character as CharacterDomain

class GetCharactersUseCase(
    private val repository: CharacterRepository,
) {

    suspend operator fun invoke(page: Int): Flow<List<CharacterDomain>> {
        return repository.getCharacters(page)
    }
}
package com.dacruz.character.domain.usecase

import com.dacruz.character.domain.CharacterRepository
import kotlinx.coroutines.flow.Flow

class IsCharacterFavoriteUseCase(private val repository: CharacterRepository) {

    suspend operator fun invoke(url: String): Flow<Boolean> {
        return repository.isCharacterFavorite(url)
    }
}
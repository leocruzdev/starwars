package com.dacruz.character.domain.usecase

import com.dacruz.character.domain.CharacterRepository

class RemoveFromFavoritesUseCase(
    private val repository: CharacterRepository
) {

    suspend operator fun invoke(url: String) {
        repository.removeFromFavorites(url)
    }
}
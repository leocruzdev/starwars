package com.dacruz.character.domain.usecase

import com.dacruz.character.domain.CharacterRepository
import com.dacruz.character.domain.model.Character as CharacterDomain

class AddToFavoritesUseCase(
    private val repository: CharacterRepository
) {

    suspend operator fun invoke(character: CharacterDomain) {
        repository.addToFavorites(character)
    }
}
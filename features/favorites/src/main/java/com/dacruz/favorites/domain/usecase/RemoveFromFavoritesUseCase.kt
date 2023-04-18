package com.dacruz.favorites.domain.usecase

import com.dacruz.favorites.domain.FavoriteRepository

class RemoveFromFavoritesUseCase(
    private val repository: FavoriteRepository
) {

    suspend operator fun invoke(url: String) {
        repository.removeFromFavorites(url)
    }
}
package com.dacruz.favorites.domain.usecase

import androidx.paging.PagingData
import com.dacruz.favorites.domain.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import com.dacruz.favorites.domain.model.FavoriteCharacter as CharacterDomain

class GetFavoriteCharactersUseCase(
    private val repository: FavoriteRepository
) {

    operator fun invoke(): Flow<PagingData<CharacterDomain>> {
        return  repository.getFavoriteCharacters()
    }
}
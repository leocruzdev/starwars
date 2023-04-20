package com.dacruz.character.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dacruz.character.domain.usecase.AddToFavoritesUseCase
import com.dacruz.character.domain.usecase.IsCharacterFavoriteUseCase
import com.dacruz.character.domain.usecase.RemoveFromFavoritesUseCase
import com.dacruz.character.presentation.mapper.FavoriteCharacteViewMapper
import com.dacruz.character.presentation.model.CharacterUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterDetailViewModel(
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
    private val isCharacterFavoriteUseCase: IsCharacterFavoriteUseCase,
    private val favoriteCharacteViewMapper: FavoriteCharacteViewMapper,
) : ViewModel() {

    fun toggleFavorite(character: CharacterUiModel, value: Boolean) {
        viewModelScope.launch {
            if (value) {
                addToFavorites(character)
            } else {
                removeFromFavorites(character)
            }
        }
    }

    suspend fun isCharacterFavorite(url: String): Flow<Boolean> {
        return isCharacterFavoriteUseCase(url)
    }

    private suspend fun addToFavorites(character: CharacterUiModel) {
        val favoriteCharacterEntity = favoriteCharacteViewMapper.toDomain(character)
        withContext(Dispatchers.IO) {
            addToFavoritesUseCase(favoriteCharacterEntity)
        }
    }

    private suspend fun removeFromFavorites(character: CharacterUiModel) {
        withContext(Dispatchers.IO) {
            removeFromFavoritesUseCase(character.url)
        }
    }
}
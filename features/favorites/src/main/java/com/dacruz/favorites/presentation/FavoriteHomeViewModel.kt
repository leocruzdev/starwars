package com.dacruz.favorites.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.dacruz.favorites.domain.usecase.GetFavoriteCharactersUseCase
import com.dacruz.favorites.presentation.mapper.FavoriteCharacterViewMapper
import com.dacruz.favorites.presentation.model.FavoriteUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteHomeViewModel(
    private val getFavoriteCharactersUseCase: GetFavoriteCharactersUseCase,
    private val favoriteCharacterViewMapper: FavoriteCharacterViewMapper,
) : ViewModel() {

    val favoriteCharacters: Flow<PagingData<FavoriteUiModel>> =
        getFavoriteCharactersUseCase()
            .map { pagingData ->
                pagingData.map { characterDomain ->
                    favoriteCharacterViewMapper.toUiModel(characterDomain)
                }
            }
            .cachedIn(viewModelScope)

}
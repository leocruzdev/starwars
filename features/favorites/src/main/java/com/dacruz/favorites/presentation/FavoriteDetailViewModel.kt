package com.dacruz.favorites.presentation

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dacruz.favorites.domain.usecase.GetCharacterImageUseCase
import com.dacruz.favorites.domain.usecase.RemoveFromFavoritesUseCase
import com.dacruz.favorites.presentation.model.FavoriteUiModel
import com.dacruz.favorites.presentation.state.FavoriteDetailState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class FavoriteDetailViewModel(
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
    private val getImageFromDatabaseUseCase: GetCharacterImageUseCase,
) : ViewModel() {

    private val _favoriteCharactersStateFlow = MutableStateFlow<FavoriteDetailState>(FavoriteDetailState.Loading)
    val favoriteCharactersStateFlow: StateFlow<FavoriteDetailState> = _favoriteCharactersStateFlow

    fun removeFromFavorites(character: FavoriteUiModel?) {
        viewModelScope.launch {
            character?.url?.let { removeFromFavoritesUseCase(it) }
         }
    }

    suspend fun getImageFromDatabase(url: String?): Flow<Bitmap?>? {
        return url?.let {
            getImageFromDatabaseUseCase(it)
                .catch { error ->
                    _favoriteCharactersStateFlow.value = FavoriteDetailState.Error(error)
                }
                .flowOn(Dispatchers.IO)
        }
    }
}
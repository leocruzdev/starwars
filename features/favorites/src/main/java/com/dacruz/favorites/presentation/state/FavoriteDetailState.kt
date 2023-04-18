package com.dacruz.favorites.presentation.state

import com.dacruz.favorites.presentation.model.FavoriteUiModel

sealed class FavoriteDetailState {
    object Loading : FavoriteDetailState()
    data class Success(val data: FavoriteUiModel?) : FavoriteDetailState()
    data class Error(val error: Throwable) : FavoriteDetailState()
    object Empty : FavoriteDetailState()
}
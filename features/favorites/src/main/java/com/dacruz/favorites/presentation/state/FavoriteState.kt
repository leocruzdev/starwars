package com.dacruz.favorites.presentation.state

sealed class FavoriteState {
    object FavoritesUpdated : FavoriteState()

    //object Loading : FavoriteState()
    //data class Success(val data: PagingData<FavoriteUiModel>) : FavoriteState()
    //data class Error(val message: Throwable) : FavoriteState()
    //object Empty : FavoriteState()
}
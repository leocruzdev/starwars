package com.dacruz.home.presentation

import com.dacruz.home.presentation.model.Category

sealed class HomeState {
    object Loading : HomeState()
    data class Success(val data: Category) : HomeState()
    data class Error(val message: Throwable) : HomeState()
    object Empty : HomeState()
}
package com.dacruz.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dacruz.home.domain.usecase.GetCategoriesHome
import com.dacruz.home.presentation.mapper.CategoryMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

internal class HomeViewModel(
    private val usecase: GetCategoriesHome,
    private val mapper: CategoryMapper
) : ViewModel() {

    private val _retryActions = MutableSharedFlow<Unit>(replay = 0)

    val homeStateFlow = _retryActions
        .onStart { emit(Unit) } // Para iniciar a carga de categorias automaticamente
        .flatMapLatest {
            loadCategories().catch { error -> emit(HomeState.Error(error)) }
        }
        .distinctUntilChanged()
        .stateIn(viewModelScope, SharingStarted.Lazily, HomeState.Loading)

    fun loadCategories(): Flow<HomeState> {
        return flow {
            usecase()
                .map { domainCategory -> mapper.toView(domainCategory) }
                .collect { category ->
                    val state = when {
                        category.categories.isNotEmpty() -> HomeState.Success(category)
                        else -> HomeState.Empty
                    }
                    emit(state)
                }
        }
    }

    fun retry() {
        viewModelScope.launch {
            _retryActions.emit(Unit)
        }
    }
}
package com.dacruz.home.presentation

import androidx.lifecycle.ViewModel
import com.dacruz.home.domain.usecase.GetCategoriesHome
import com.dacruz.home.presentation.mapper.CategoryMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

internal class HomeViewModel(
    private val usecase: GetCategoriesHome,
    private val mapper: CategoryMapper
) : ViewModel() {

    fun loadCategories(): Flow<HomeState> {
        return flow {
            usecase()
                .map { domainCategory -> mapper.toView(domainCategory) }
                .catch { error -> emit(HomeState.Error(error)) }
                .collect { category ->
                    val state = when {
                        category.categories.isNotEmpty() -> HomeState.Success(category)
                        else -> HomeState.Empty
                    }
                    emit(state)
                }
        }
    }
}
package com.dacruz.home.domain.usecase

import com.dacruz.home.domain.HomeRepository
import com.dacruz.home.domain.model.Category
import kotlinx.coroutines.flow.Flow

class GetCategoriesHome(private val repository: HomeRepository) {

    suspend operator fun invoke(): Flow<Category> {
        return repository.getCategories()
    }
}
package com.dacruz.home.domain

import com.dacruz.home.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getCategories(): Flow<Category>
}
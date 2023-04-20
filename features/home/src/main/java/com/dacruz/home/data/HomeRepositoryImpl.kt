package com.dacruz.home.data

import com.dacruz.home.data.mapper.CategoryMapper
import com.dacruz.home.data.service.HomeService
import com.dacruz.home.domain.HomeRepository
import com.dacruz.home.domain.model.Category
import com.dacruz.networking.executeRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepositoryImpl(
    private val service: HomeService,
    private val mapper: CategoryMapper
) : HomeRepository {

    override suspend fun getCategories(): Flow<Category> {
        return flow {
            executeRequest {
                val data = service.getCategories()
                val mappedData = mapper.toDomain(data)
                emit(mappedData)
            }
        }
    }
}
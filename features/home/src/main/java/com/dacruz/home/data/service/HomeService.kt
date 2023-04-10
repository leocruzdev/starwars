package com.dacruz.home.data.service

import com.dacruz.home.data.model.Category
import retrofit2.http.GET

interface HomeService {
    @GET("api/")
    suspend fun getCategories(): Category
}
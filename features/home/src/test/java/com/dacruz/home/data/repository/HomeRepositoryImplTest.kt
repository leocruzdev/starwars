package com.dacruz.home.data.repository

import com.dacruz.home.data.HomeRepositoryImpl
import com.dacruz.home.data.mapper.CategoryMapper
import com.dacruz.home.data.service.HomeService
import com.dacruz.home.domain.HomeRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import com.dacruz.home.data.model.Category as DataCategory
import com.dacruz.home.domain.model.Category as DomainCategory

class HomeRepositoryImplTest {

    private lateinit var repository: HomeRepository

    private val mockService = mockk<HomeService>()
    private val mockMapper = mockk<CategoryMapper>()

    @Before
    fun setUp() {
        repository = HomeRepositoryImpl(mockService, mockMapper)
    }


    @Test
    fun `test Mapping Categories Returns Mapped Categories`() = runBlocking {
        val fakeApiResponse = DataCategory(starships = "", species = "", planets = "", people = "", films = "", vehicles = "")
        val expectedCategories = DomainCategory(starships = "", species = "", planets = "", people = "", films = "", vehicles = "")

        coEvery { mockService.getCategories() } returns fakeApiResponse
        every { mockMapper.toDomain(fakeApiResponse) } returns expectedCategories

        val result = repository.getCategories().first()

        coVerify { mockService.getCategories() }
        verify { mockMapper.toDomain(fakeApiResponse) }
        assertEquals(expectedCategories, result)
    }
}
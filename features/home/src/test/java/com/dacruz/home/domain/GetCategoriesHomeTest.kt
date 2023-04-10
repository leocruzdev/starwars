package com.dacruz.home.domain

import com.dacruz.home.domain.usecase.GetCategoriesHome
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import com.dacruz.home.domain.model.Category as DomainCategory

class GetCategoriesHomeTest {

    private lateinit var useCase: GetCategoriesHome
    private lateinit var mockRepository: HomeRepository

    @Before
    fun setup() {
        mockRepository = mockk()
        useCase = GetCategoriesHome(mockRepository)
    }

    @Test
    fun `should return expected categories`() = runBlocking {
        // Given
        val fakeCategory = DomainCategory(starships = "", species = "", planets = "", people = "", films = "", vehicles = "")
        val expected = DomainCategory(starships = "", species = "", planets = "", people = "", films = "", vehicles = "")

        coEvery { mockRepository.getCategories() } returns flowOf(expected)

        // When
        val result = useCase.invoke().first()

        // Then
        assertEquals(fakeCategory, result)
        coVerify { mockRepository.getCategories() }
    }


}
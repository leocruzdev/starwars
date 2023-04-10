package com.dacruz.home.presentation

import com.dacruz.home.presentation.mapper.CategoryMapper
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import com.dacruz.home.domain.model.Category as DomainCategory
import com.dacruz.home.presentation.model.Category as ViewCategory

class CategoryMapperTest {

    private lateinit var mapper: CategoryMapper

    @Before
    fun setup() {
        mapper = CategoryMapper()
    }

    @Test
    fun `should map DomainCategory to ViewCategory`() {
        // Given
        val domainCategory = DomainCategory(
            people = "1", planets = "2", films = "3", species = "4", vehicles = "5", starships = "6"
        )
        val expectedViewCategory = ViewCategory(
            categories = listOf(
                "Pessoas" to "1",
                "Planetas" to "2",
                "Filmes" to "3",
                "Espécies" to "4",
                "Veículos" to "5",
                "Naves" to "6"
            )
        )

        // When
        val result = mapper.toView(domainCategory)

        // Then
        assertEquals(expectedViewCategory, result)
    }

    @Test
    fun `should map ViewCategory to DomainCategory`() {
        // Given
        val viewCategory = ViewCategory(
            categories = listOf(
                "Pessoas" to "1",
                "Planetas" to "2",
                "Filmes" to "3",
                "Espécies" to "4",
                "Veículos" to "5",
                "Naves" to "6"
            )
        )
        val expectedDomainCategory = DomainCategory(
            people = "1", planets = "2", films = "3", species = "4", vehicles = "5", starships = "6"
        )

        // When
        val result = mapper.toDomain(viewCategory)

        // Then
        assertEquals(expectedDomainCategory, result)
    }

    @Test
    fun `should map list of DomainCategory to list of ViewCategory`() {
        // Given
        val domainCategoryList = listOf(
            DomainCategory(
                people = "1", planets = "2", films = "3", species = "4", vehicles = "5", starships = "6"
            ),
            DomainCategory(
                people = "7", planets = "8", films = "9", species = "10", vehicles = "11", starships = "12"
            )
        )
        val expectedViewCategoryList = listOf(
            ViewCategory(
                categories = listOf(
                    "Pessoas" to "1",
                    "Planetas" to "2",
                    "Filmes" to "3",
                    "Espécies" to "4",
                    "Veículos" to "5",
                    "Naves" to "6"
                )
            ),
            ViewCategory(
                categories = listOf(
                    "Pessoas" to "7",
                    "Planetas" to "8",
                    "Filmes" to "9",
                    "Espécies" to "10",
                    "Veículos" to "11",
                    "Naves" to "12"
                )
            )
        )

        // When
        val result = mapper.toView(domainCategoryList)

        // Then
        assertEquals(expectedViewCategoryList, result)
    }

    @Test
    fun `should map list of ViewCategory to list of DomainCategory`() {
        // Given
        val viewCategoryList = listOf(
            ViewCategory(
                categories = listOf(
                    "Pessoas" to "1",
                    "Planetas" to "2",
                    "Filmes" to "3",
                    "Espécies" to "4",
                    "Veículos" to "5",
                    "Naves" to "6"
                )
            ),
            ViewCategory(
                categories = listOf(
                    "Pessoas" to "7",
                    "Planetas" to "8",
                    "Filmes" to "9",
                    "Espécies" to "10",
                    "Veículos" to "11",
                    "Naves" to "12"
                )
            )
        )
        val expectedDomainCategoryList = listOf(
            DomainCategory(
                people = "1", planets = "2", films = "3", species = "4", vehicles = "5", starships = "6"
            ),
            DomainCategory(
                people = "7", planets = "8", films = "9", species = "10", vehicles = "11", starships = "12"
            )
        )

        // When
        val result = mapper.toDomain(viewCategoryList)

        // Then
        assertEquals(expectedDomainCategoryList, result)
    }
}
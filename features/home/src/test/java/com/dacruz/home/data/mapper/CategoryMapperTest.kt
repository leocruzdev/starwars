package com.dacruz.home.data.mapper

import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import com.dacruz.home.data.model.Category as DataCategory
import com.dacruz.home.domain.model.Category as DomainCategory

class CategoryMapperTest {
    private var domainCategory: DomainCategory = mockk()
    private var dataCategory: DataCategory = mockk()
    private val mapper = CategoryMapper()

    companion object {
        const val PEOPLE = "people"
        const val PLANETS = "planets"
        const val FILMS = "films"
        const val SPECIES = "species"
        const val VEHICLES = "vehicles"
        const val STARSHIPS = "starships"
    }

    @Before
    fun setUp() {
        domainCategory =  mockk {
            every { people } returns PEOPLE
            every { planets } returns PLANETS
            every { films } returns FILMS
            every { species } returns SPECIES
            every { vehicles } returns VEHICLES
            every { starships } returns STARSHIPS
        }

        dataCategory =  mockk {
            every { people } returns PEOPLE
            every { planets } returns PLANETS
            every { films } returns FILMS
            every { species } returns SPECIES
            every { vehicles } returns VEHICLES
            every { starships } returns STARSHIPS
        }
    }

    @Test
    fun `test toDomain single item`() {
        val domainCategory = mapper.toDomain(dataCategory)

        assertEquals("people", domainCategory.people)
        assertEquals("planets", domainCategory.planets)
        assertEquals("films", domainCategory.films)
        assertEquals("species", domainCategory.species)
        assertEquals("vehicles", domainCategory.vehicles)
        assertEquals("starships", domainCategory.starships)
    }

    @Test
    fun `test toDomain list`() {
        val dataCategoryList = listOf(dataCategory, dataCategory)
        val domainCategoryList = mapper.toDomain(dataCategoryList)

        assertEquals(2, domainCategoryList.size)
        assertEquals("people", domainCategoryList[0].people)
        assertEquals("planets", domainCategoryList[0].planets)
        assertEquals("films", domainCategoryList[0].films)
        assertEquals("species", domainCategoryList[0].species)
        assertEquals("vehicles", domainCategoryList[0].vehicles)
        assertEquals("starships", domainCategoryList[0].starships)
    }

    @Test
    fun `test toData single item`() {
        val dataCategory = mapper.toData(domainCategory)

        assertEquals("people", dataCategory.people)
        assertEquals("planets", dataCategory.planets)
        assertEquals("films", dataCategory.films)
        assertEquals("species", dataCategory.species)
        assertEquals("vehicles", dataCategory.vehicles)
        assertEquals("starships", dataCategory.starships)
    }

    @Test
    fun `test toData list`() {
        val domainCategoryList = listOf(domainCategory, domainCategory)
        val dataCategoryList = mapper.toData(domainCategoryList)

        assertEquals(2, dataCategoryList.size)
        assertEquals("people", dataCategoryList[0].people)
        assertEquals("planets", dataCategoryList[0].planets)
        assertEquals("films", dataCategoryList[0].films)
        assertEquals("species", dataCategoryList[0].species)
        assertEquals("vehicles", dataCategoryList[0].vehicles)
        assertEquals("starships", dataCategoryList[0].starships)
    }

}
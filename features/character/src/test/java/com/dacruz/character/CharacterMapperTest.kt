package com.dacruz.character

import com.dacruz.character.data.mapper.CharacterDataMapper
import com.dacruz.character.data.model.CharacterDTO
import com.dacruz.character.data.model.CharacterListDTO
import junit.framework.TestCase.assertEquals
import org.junit.Test
import com.dacruz.character.domain.model.Character as CharacterDomain

class CharacterMapperTest {

    private val characterDataMapper = CharacterDataMapper()

    @Test
    fun `toDomain should correctly map CharacterDTO to CharacterDomain`() {
        // Given
        val characterDTO = CharacterDTO(
            name = "Luke Skywalker",
            height = "172",
            mass = "77",
            hairColor = "blond",
            skinColor = "fair",
            eyeColor = "blue",
            birthYear = "19BBY",
            gender = "male",
            homeWorldUrl = "https://swapi.dev/api/planets/1/",
            filmsUrls = listOf("https://swapi.dev/api/films/1/"),
            speciesUrls = listOf(),
            vehiclesUrls = listOf("https://swapi.dev/api/vehicles/14/"),
            starshipsUrls = listOf("https://swapi.dev/api/starships/12/"),
            created = "2014-12-09T13:50:51.644000Z",
            edited = "2014-12-20T21:17:56.891000Z",
            url = "https://swapi.dev/api/people/1/"
        )

        val expectedCharacterDomain = CharacterDomain(
            name = "Luke Skywalker",
            height = "172",
            mass = "77",
            hairColor = "blond",
            skinColor = "fair",
            eyeColor = "blue",
            birthYear = "19BBY",
            gender = "male",
            url = "https://swapi.dev/api/people/1/"
        )

        // When
        val mappedCharacterDomain = characterDataMapper.toDomain(characterDTO)

        // Then
        assertEquals(expectedCharacterDomain, mappedCharacterDomain)
    }

    @Test
    fun `toDomain should correctly map CharacterListDTO to List of CharacterDomain`() {
        // Given
        val characterDTO = CharacterDTO(
            name = "Luke Skywalker",
            height = "172",
            mass = "77",
            hairColor = "blond",
            skinColor = "fair",
            eyeColor = "blue",
            birthYear = "19BBY",
            gender = "male",
            homeWorldUrl = "https://swapi.dev/api/planets/1/",
            filmsUrls = listOf("https://swapi.dev/api/films/1/"),
            speciesUrls = listOf(),
            vehiclesUrls = listOf("https://swapi.dev/api/vehicles/14/"),
            starshipsUrls = listOf("https://swapi.dev/api/starships/12/"),
            created = "2014-12-09T13:50:51.644000Z",
            edited = "2014-12-20T21:17:56.891000Z",
            url = "https://swapi.dev/api/people/1/"
        )

        val characterListDTO = CharacterListDTO(
            count = 1,
            next = null,
            previous = null,
            characters = listOf(characterDTO)
        )

        val expectedCharacterDomainList = listOf(
            CharacterDomain(
                name = "Luke Skywalker",
                height = "172",
                mass = "77",
                hairColor = "blond",
                skinColor = "fair",
                eyeColor = "blue",
                birthYear = "19BBY",
                gender = "male",
                url = "https://swapi.dev/api/people/1/"
            )
        )

        // When
        val mappedCharacterDomainList = characterDataMapper.toDomain(characterListDTO)

        // Then
        assertEquals(expectedCharacterDomainList, mappedCharacterDomainList)
    }
}
package com.dacruz.character

import com.dacruz.character.domain.CharacterRepository
import com.dacruz.character.domain.usecase.GetCharactersUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import com.dacruz.character.domain.model.Character as CharacterDomain

class GetCharactersUseCaseTest {

    private val repository = mockk<CharacterRepository>()
    private val getCharactersUseCase = GetCharactersUseCase(repository)

    @Test
    fun `invoke should return a flow of characters`() = runBlockingTest {
        // Given
        val page = 1
        val characters = listOf(
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
            ),
            CharacterDomain(
                name = "Darth Vader",
                height = "202",
                mass = "136",
                hairColor = "none",
                skinColor = "white",
                eyeColor = "yellow",
                birthYear = "41.9BBY",
                gender = "male",
                url = "https://swapi.dev/api/people/4/"
            )
        )

        coEvery { repository.getCharacters(page) } returns flowOf(characters)

        // When
        val result = getCharactersUseCase(page).first()

        // Then
        assertEquals(characters, result)
    }
}
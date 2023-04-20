package com.dacruz.character

import com.dacruz.character.presentation.mapper.CharacterViewMapper
import com.dacruz.character.presentation.model.CharacterUiModel
import junit.framework.TestCase.assertEquals
import org.junit.Test
import com.dacruz.character.domain.model.Character as CharacterDomain

class CharacterViewMapperTest {

    private val characterViewMapper = CharacterViewMapper()

    @Test
    fun `toView should convert domainCharacter to CharacterUiModel`() {
        // Given
        val domainCharacter = CharacterDomain(
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
        val expectedUiCharacter = CharacterUiModel(
            name = "Luke Skywalker",
            height = "172",
            mass = "77",
            hairColor = "blond",
            skinColor = "fair",
            eyeColor = "blue",
            birthYear = "19BBY",
            gender = "male",
            url = "https://swapi.dev/api/people/1/",
            characterImage = "https://starwars-visualguide.com/assets/img/characters/1.jpg"
        )

        // When
        val uiCharacter = characterViewMapper.toView(domainCharacter)

        // Then
        assertEquals(expectedUiCharacter, uiCharacter)
    }


}
package com.dacruz.character.presentation.mapper

import  com.dacruz.character.domain.model.Character
import com.dacruz.character.presentation.model.CharacterUiModel

class CharacterViewMapper {

    fun toView(domainCharacter: Character): CharacterUiModel {
        val characterIndex = extractCharacterIndex(domainCharacter.url)
        val imageUrl = "$IMAGE_CHARACTER_URL$characterIndex.jpg"
        return CharacterUiModel(
            name = domainCharacter.name,
            height = domainCharacter.height,
            mass = domainCharacter.mass,
            hairColor = domainCharacter.hairColor,
            skinColor = domainCharacter.skinColor,
            eyeColor = domainCharacter.eyeColor,
            birthYear = domainCharacter.birthYear,
            gender = domainCharacter.gender,
            url = domainCharacter.url,
            characterImage = imageUrl
        )
    }

    fun toView(domainCharacterList: List<Character>): List<CharacterUiModel> {
        return domainCharacterList.map { toView(it) }
    }

    fun toDomain(uiCharacter: CharacterUiModel): Character {
        return Character(
            name = uiCharacter.name,
            height = uiCharacter.height,
            mass = uiCharacter.mass,
            hairColor = uiCharacter.hairColor,
            skinColor = uiCharacter.skinColor,
            eyeColor = uiCharacter.eyeColor,
            birthYear = uiCharacter.birthYear,
            gender = uiCharacter.gender,
            url = uiCharacter.url
        )
    }

    fun toDomain(uiCharacterList: List<CharacterUiModel>): List<Character> {
        return uiCharacterList.map { toDomain(it) }
    }

    private fun extractCharacterIndex(url: String): Int {
        val regex = "https://swapi.dev/api/people/(\\d+)/".toRegex()
        return regex.matchEntire(url)?.groupValues?.get(1)?.toIntOrNull() ?: 0
    }

    companion object {
        private const val IMAGE_CHARACTER_URL =
            "https://starwars-visualguide.com/assets/img/characters/"
    }

}
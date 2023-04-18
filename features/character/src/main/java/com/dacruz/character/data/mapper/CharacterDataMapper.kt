package com.dacruz.character.data.mapper

import com.dacruz.character.data.model.CharacterDTO
import com.dacruz.character.data.model.CharacterListDTO
import com.dacruz.character.domain.model.Character as CharacterDomain

class CharacterDataMapper {

    fun toDomain(dataCharacter: CharacterDTO, isFavorite: Boolean = false): CharacterDomain =
        CharacterDomain(
            name = dataCharacter.name,
            height = dataCharacter.height,
            mass = dataCharacter.mass,
            hairColor = dataCharacter.hairColor,
            skinColor = dataCharacter.skinColor,
            eyeColor = dataCharacter.eyeColor,
            birthYear = dataCharacter.birthYear,
            gender = dataCharacter.gender,
            url = dataCharacter.url,
            isFavorite = isFavorite
        )

    fun toDomain(dataCharacterList: CharacterListDTO): List<CharacterDomain> =
        dataCharacterList.characters.map { toDomain(it) }

}
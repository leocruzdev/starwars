package com.dacruz.character.data.service

import com.dacruz.character.data.model.CharacterListDTO
import retrofit2.http.GET
import retrofit2.http.Url

interface CharacterService {

    @GET
    suspend fun getCharacters(@Url url: String): CharacterListDTO
}
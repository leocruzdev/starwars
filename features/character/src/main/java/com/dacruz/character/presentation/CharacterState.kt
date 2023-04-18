package com.dacruz.character.presentation

import com.dacruz.character.presentation.model.CharacterUiModel


sealed class CharacterState {
    object Loading : CharacterState()
    data class Success(val data: List<CharacterUiModel>) : CharacterState()
    data class Error(val message: Throwable) : CharacterState()
    object Empty : CharacterState()
}
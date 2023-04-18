package com.dacruz.character.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dacruz.character.domain.usecase.GetCharactersUseCase
import com.dacruz.character.presentation.mapper.CharacterViewMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

internal class CharactersViewModel(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val characterViewMapper: CharacterViewMapper
) : ViewModel() {

    private var currentPage = 1

    private val _characterStateFlow = MutableStateFlow<CharacterState>(CharacterState.Loading)
    val characterStateFlow: StateFlow<CharacterState> = _characterStateFlow

    private val _filteredCharacterStateFlow =
        MutableStateFlow<CharacterState>(CharacterState.Loading)
    val filteredCharacterStateFlow: StateFlow<CharacterState> = _filteredCharacterStateFlow

    private val _isLoadingMore = MutableStateFlow(false)
    val isLoadingMore: StateFlow<Boolean> = _isLoadingMore

    val searchQueryFlow = MutableStateFlow("")

    init {
        loadCharacters()

        viewModelScope.launch {
            searchQueryFlow
                .debounce(300)
                .collect { query ->
                    updateFilter(query)
                }
        }
    }

    private fun loadCharacters(autoLoadMore: Boolean = false) {
        viewModelScope.launch {
            getCharactersUseCase(currentPage)
                .map { domainCharacters -> characterViewMapper.toView(domainCharacters) }
                .catch { error -> _characterStateFlow.value = CharacterState.Error(error) }
                .collect { uiCharacters ->
                    val state = when {
                        uiCharacters.isNotEmpty() -> CharacterState.Success(uiCharacters)
                        else -> CharacterState.Empty
                    }
                    _characterStateFlow.value = state

                    if (autoLoadMore && state is CharacterState.Success) {
                        loadMoreCharacters()
                    }
                }
        }
    }

    fun loadMoreCharacters() {
        if (searchQueryFlow.value.isEmpty() && !_isLoadingMore.value) {
            currentPage += 1
            if (currentPage <= 10) _isLoadingMore.value = true
            viewModelScope.launch {
                getCharactersUseCase(currentPage)
                    .map { domainCharacters -> characterViewMapper.toView(domainCharacters) }
                    .catch { error -> _characterStateFlow.value = CharacterState.Error(error) }
                    .collect { uiCharacters ->
                        if (uiCharacters.isNotEmpty()) {
                            val currentState = _characterStateFlow.value
                            if (currentState is CharacterState.Success) {
                                val updatedList =
                                    currentState.data + uiCharacters.map { it.copy(isFavorite = false) }

                                val newState = CharacterState.Success(updatedList)
                                _characterStateFlow.value = newState
                            }
                        }
                        _isLoadingMore.value = false
                    }
            }
        }
    }

    fun retry() {
        if (_characterStateFlow.value is CharacterState.Error) {
            currentPage = 1
            loadCharacters()
        }
     }

    private fun updateFilter(query: String) {
        val currentState = _characterStateFlow.value
        if (currentState is CharacterState.Success) {
            val filteredList = currentState.data.filter { character ->
                character.name.contains(query, ignoreCase = true)
            }
            _filteredCharacterStateFlow.value = CharacterState.Success(filteredList)
        }
    }
}
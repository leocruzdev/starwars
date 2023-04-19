package com.dacruz.character.presentation


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dacruz.character.presentation.model.CharacterUiModel
import com.dacruz.characters.R
import com.dacruz.navigator.NavigationHandler
import com.dacruz.navigator.Screen
import com.dacruz.theme.components.DefaultListItem
import com.dacruz.theme.components.DefaultSearchField
import com.dacruz.theme.components.DefaultToolbar
import com.dacruz.theme.components.LoadingButton
import com.dacruz.theme.components.withErrorHandling
import org.koin.androidx.compose.getViewModel

@Composable
fun CharacterScreen(navigationHandler: NavigationHandler) {
    val charactersViewModel: CharactersViewModel = getViewModel()

    val characterState by charactersViewModel.characterStateFlow.collectAsState()
    val filteredCharacterState by charactersViewModel.filteredCharacterStateFlow.collectAsState()

    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            DefaultToolbar(
                title = stringResource(id = R.string.character_screen_title),
                onUpPress = { navigationHandler.navigateBack() }
            )
        }
    ) {
        it
        Column {
            DefaultSearchField(
                value = searchQuery,
                onValueChange = { newQuery ->
                    searchQuery = newQuery
                    charactersViewModel.searchQueryFlow.value = newQuery
                },
                label = stringResource(id = R.string.character_search_hint),
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth(),
            )

            CharacterScreenContent(
                showLoadMoreButton = searchQuery.isEmpty(),
                characterState = if (searchQuery.isEmpty()) characterState else filteredCharacterState,
                retryClick = {
                    charactersViewModel.retry()
                },
                loadMore = { charactersViewModel.loadMoreCharacters() },
                navigationHandler = navigationHandler,
            )
        }
    }
}

@Composable
private fun CharacterScreenContent(
    characterState: CharacterState,
    retryClick: () -> Unit,
    loadMore: () -> Unit,
    navigationHandler: NavigationHandler,
    showLoadMoreButton: Boolean = false,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        when (characterState) {
            is CharacterState.Loading -> {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.onSurface
                )
            }
            is CharacterState.Success -> {
                CharacterList(
                    characters = characterState.data,
                    loadMore = loadMore,
                    navigationHandler = navigationHandler,
                    showLoadMoreButton = showLoadMoreButton,
                )
            }
            is CharacterState.Error -> {
                withErrorHandling(
                    errorMessage = characterState.message.localizedMessage,
                    retryAction = retryClick
                )
            }
            is CharacterState.Empty -> {
                Text(text = stringResource(id = R.string.character_list_empty))
            }
        }
    }
}


@Composable
private fun CharacterList(
    characters: List<CharacterUiModel>,
    loadMore: () -> Unit,
    navigationHandler: NavigationHandler,
    showLoadMoreButton: Boolean,
) {
    val listState = rememberLazyListState()

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = listState,
            modifier = Modifier.weight(1f)
        ) {
            items(characters) { character ->
                CharacterListItem(character, navigationHandler)
            }

            if (showLoadMoreButton) {
                item {
                    LoadingButton(
                        onClick = {
                            loadMore()
                        },
                        text = stringResource(R.string.character_load_more),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                    )
                }
            }
        }
    }
}


@Composable
private fun CharacterListItem(
    character: CharacterUiModel,
    navigationHandler: NavigationHandler
) {
    DefaultListItem(
        imageUrl = character.characterImage,
        title = character.name,
        onClick = {
            navigationHandler.navigateTo(
                screen = Screen.CharacterDetailScreen, extra = character
            )
        },
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp),
        imageModifier = Modifier
            .height(64.dp)
            .width(64.dp),
    )
}
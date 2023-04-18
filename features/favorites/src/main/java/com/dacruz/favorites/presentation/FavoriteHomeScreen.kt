package com.dacruz.favorites.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.dacruz.favorites.R
import com.dacruz.favorites.presentation.model.FavoriteUiModel
import com.dacruz.navigator.NavigationHandler
import com.dacruz.navigator.Screen
import com.dacruz.theme.components.DefaultListItem
import com.dacruz.theme.components.DefaultToolbar
import com.dacruz.theme.components.withErrorHandling
import org.koin.androidx.compose.getViewModel


@Composable
fun FavoriteHomeScreen(navigationHandler: NavigationHandler) {
    val favoriteHomeViewModel: FavoriteHomeViewModel = getViewModel()
    val pagingItems = favoriteHomeViewModel.favoriteCharacters.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            DefaultToolbar(
                title = stringResource(id = R.string.favorite_characters_title),
                onUpPress = { navigationHandler.navigateBack() }
            )
        }
    ) {
        it
        Column {
            FavoriteCharacterList(
                pagingItems = pagingItems,
                navigationHandler = navigationHandler,
            )
        }
    }
}


@Composable
private fun FavoriteCharacterList(
    pagingItems: LazyPagingItems<FavoriteUiModel>,
    navigationHandler: NavigationHandler,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            if (pagingItems.itemSnapshotList != pagingItems) pagingItems.refresh()
            items(
                items = pagingItems,
                itemContent = { favoriteCharacter ->
                    favoriteCharacter?.let {
                        FavoriteCharacterListItem(
                            favoriteCharacter = it,
                            navigationHandler = navigationHandler
                        )
                    }
                }
            )

            when (pagingItems.loadState.append) {
                is LoadState.Loading -> {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                    }
                }
                is LoadState.Error -> {
                    item {
                        withErrorHandling(
                            retryAction = { pagingItems.retry() },
                            errorMessage = stringResource(id = R.string.favorite_characters_error_message),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                    }
                }
                else -> {}
            }
        }
    }
}


@Composable
private fun FavoriteCharacterListItem(
    favoriteCharacter: FavoriteUiModel,
    navigationHandler: NavigationHandler
) {
    DefaultListItem(
        imageBitmap = favoriteCharacter.favoriteImage?.asAndroidBitmap(),
        title = favoriteCharacter.name,
        onClick = {
            navigationHandler.navigateTo(
                screen = Screen.FavoriteDetailScreen, extra = favoriteCharacter
            )
        },
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp),
        imageModifier = Modifier
            .height(64.dp)
            .width(64.dp),
    )
}
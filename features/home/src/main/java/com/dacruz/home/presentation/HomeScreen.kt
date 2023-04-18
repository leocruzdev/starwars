package com.dacruz.home.presentation

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness4
import androidx.compose.material.icons.filled.Brightness7
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dacruz.home.R
import com.dacruz.home.presentation.model.Category
import com.dacruz.navigator.NavigationHandler
import com.dacruz.navigator.Screen
import com.dacruz.theme.Grey100
import com.dacruz.theme.Grey900
import com.dacruz.theme.LocalIsLightTheme
import com.dacruz.theme.StarWarsTheme
import com.dacruz.theme.components.DefaultImage
import com.dacruz.theme.components.DefaultToolbar
import com.dacruz.theme.components.withErrorHandling
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(navigationHandler: NavigationHandler) {
    val viewModel: HomeViewModel = getViewModel()
    val homeState by viewModel.homeStateFlow.collectAsState(initial = HomeState.Loading)

    val isLightTheme = LocalIsLightTheme.current
    val themeButtonColor by animateColorAsState(
        targetValue = if (isLightTheme.value) Grey900 else Grey100,
        animationSpec = TweenSpec(durationMillis = 500)
    )

    StarWarsTheme(isDarkTheme = isLightTheme.value) {
        Scaffold(
            topBar = {
                DefaultToolbar(
                    title = stringResource(id = R.string.home_title),
                    shouldShowNavigationIcon = false,
                    alternativeIcon = Icons.Default.Home
                )
            },
            content = {
                it
                Surface(color = MaterialTheme.colors.surface) {
                    HomeScreenContent(homeState, viewModel, navigationHandler)
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter
                    ) {

                        Button(
                            onClick = { navigationHandler.navigateTo(Screen.FavoriteListScreen) },
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(text = stringResource(id = R.string.favorite_list))
                        }
                    }
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
                        FloatingActionButton(
                            onClick = { isLightTheme.value = !isLightTheme.value },
                            backgroundColor = themeButtonColor,
                            contentColor = MaterialTheme.colors.onSurface,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Crossfade(targetState = isLightTheme.value) { isLight ->
                                if (isLight) {
                                    Icon(
                                        imageVector = Icons.Filled.Brightness7,
                                        contentDescription = stringResource(id = R.string.switch_theme_light)
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Filled.Brightness4,
                                        contentDescription = stringResource(id = R.string.switch_theme_dark)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}

@Composable
private fun HomeScreenContent(
    homeState: HomeState,
    viewModel: HomeViewModel,
    navigationHandler: NavigationHandler
) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        when (homeState) {
            is HomeState.Loading -> {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.onSurface
                )
            }
            is HomeState.Success -> {
                GridLayout(homeState.data, navigationHandler)
            }
            is HomeState.Error -> {
                withErrorHandling(
                    errorMessage = homeState.message.localizedMessage,
                    retryAction = { viewModel.retry() }
                )
            }

            is HomeState.Empty -> {
                Text(text = stringResource(id = R.string.none_found))
            }
        }
    }
}


@Composable
fun GridLayout(category: Category, navigationHandler: NavigationHandler) {
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyVerticalGrid(
            horizontalArrangement = Arrangement.Center,
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(category.categories) { category ->
                CategoryCard(
                    categoryName = category.first,
                    urlImage = category.third,
                    navigationHandler = navigationHandler
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryCard(categoryName: String, urlImage: String, navigationHandler: NavigationHandler) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.primary,
        onClick = {
            navigationHandler.navigateTo(Screen.CharacterScreen)
        }
    ) {
        Column {
            DefaultImage(
                imageUrl = urlImage,
                contentDescription = stringResource(
                    R.string.category_image_content_description,
                    categoryName
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    style = MaterialTheme.typography.body1,
                    text = categoryName,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}
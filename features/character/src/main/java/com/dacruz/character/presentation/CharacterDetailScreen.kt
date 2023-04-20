package com.dacruz.character.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dacruz.character.presentation.model.CharacterUiModel
import com.dacruz.characters.R
import com.dacruz.navigator.NavigationHandler
import com.dacruz.navigator.collectAsType
import com.dacruz.theme.Red
import com.dacruz.theme.components.DefaultImage
import com.dacruz.theme.components.DefaultToolbar
import com.dacruz.theme.components.DetailCard
import org.koin.androidx.compose.getViewModel
import com.dacruz.theme.R as theme

@Composable
fun CharacterDetailScreen(navigationHandler: NavigationHandler) {
    val characterDetailViewModel: CharacterDetailViewModel = getViewModel()
    val character = navigationHandler.transportedData.collectAsType<CharacterUiModel>()

    Scaffold(
        topBar = {
            DefaultToolbar(
                title = stringResource(R.string.detail_screen_title),
                shouldShowNavigationIcon = true,
                onUpPress = { navigationHandler.navigateBack() },
            )
        },
        content = {
            it
            CharacterDetailContent(character, characterDetailViewModel)
        }
    )
}

@Composable
private fun CharacterDetailContent(
    character: CharacterUiModel?,
    characterDetailViewModel: CharacterDetailViewModel
) {
    val isFavorite = remember { mutableStateOf(character?.isFavorite) }

    LaunchedEffect(character?.url) {
        character?.url?.let {
            characterDetailViewModel.isCharacterFavorite(it).collect { isFav ->
                isFavorite.value = isFav
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            character?.characterImage?.let { imageUrl ->
                DefaultImage(
                    imageUrl = imageUrl,
                    getBitmapFromUrl = true,
                    onSuccess = { imageBitmap ->
                        character.favoriteImage = imageBitmap
                    },
                    modifier = Modifier
                        .height(120.dp)
                        .width(120.dp)
                        .clip(CircleShape)
                )
            }
        }

        DetailCard(
            title = character?.name,
            fields = listOf(
                stringResource(theme.string.detail_gender) to character?.gender,
                stringResource(theme.string.detail_height) to character?.height,
                stringResource(theme.string.detail_eye_color) to character?.eyeColor,
                stringResource(theme.string.detail_skin_color) to character?.skinColor
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            ExtendedFloatingActionButton(
                onClick = {
                    characterDetailViewModel.toggleFavorite(
                        character!!,
                        isFavorite.value!!.not()
                    )
                },
                icon = {
                    Icon(
                        imageVector = if (isFavorite.value == true) {
                            Icons.Filled.Favorite
                        } else {
                            Icons.Outlined.FavoriteBorder
                        },
                        contentDescription = stringResource(R.string.detail_favorite_button_content),
                        tint = if (isFavorite.value == true) Red else MaterialTheme.colors.onSurface
                    )
                },
                text = { Text(stringResource(R.string.detail_favorite)) },
                backgroundColor = MaterialTheme.colors.surface
            )
        }
    }
}
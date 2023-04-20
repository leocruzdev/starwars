package com.dacruz.favorites.presentation

import android.graphics.Bitmap
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
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dacruz.favorites.R
import com.dacruz.favorites.presentation.model.FavoriteUiModel
import com.dacruz.navigator.NavigationHandler
import com.dacruz.navigator.collectAsType
import com.dacruz.theme.components.DefaultImage
import com.dacruz.theme.components.DefaultToolbar
import com.dacruz.theme.components.DetailCard
import org.koin.androidx.compose.getViewModel
import org.koin.compose.rememberKoinInject
import com.dacruz.theme.R as theme

@Composable
fun FavoriteDetailScreen(navigationHandler: NavigationHandler) {
    val favoriteDetailViewModel: FavoriteDetailViewModel = getViewModel()
    val character = navigationHandler.transportedData.collectAsType<FavoriteUiModel>()

    Scaffold(
        topBar = {
            DefaultToolbar(
                title = stringResource(id = R.string.favorite_detail_title),
                shouldShowNavigationIcon = true,
                onUpPress = { navigationHandler.navigateBack() },
            )
        },
        content = {
            it
            CharacterDetailContent(
                character,
                favoriteDetailViewModel
            )
        }
    )
}


@Composable
private fun CharacterDetailContent(
    character: FavoriteUiModel?,
    favoriteDetailViewModel: FavoriteDetailViewModel,
    navigationHandler: NavigationHandler = rememberKoinInject()
) {
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    LaunchedEffect(character?.url) {
        favoriteDetailViewModel.getImageFromDatabase(character?.url)
            ?.collect { imageBitmap ->
                bitmap.value = imageBitmap
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
            DefaultImage(
                bitmap = bitmap.value,
                modifier = Modifier
                    .height(120.dp)
                    .width(120.dp)
                    .clip(CircleShape)
            )
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
                    favoriteDetailViewModel.removeFromFavorites(character = character)
                    navigationHandler.navigateBack()
                },
                icon = {
                    Icon(
                        imageVector = Filled.Delete,
                        contentDescription = stringResource(id = R.string.favorite_detail_remove_button),
                        tint = MaterialTheme.colors.onSurface
                    )
                },
                text = { Text(stringResource(id = R.string.favorite_detail_remove_button)) },
                backgroundColor = MaterialTheme.colors.surface
            )
        }
    }
}
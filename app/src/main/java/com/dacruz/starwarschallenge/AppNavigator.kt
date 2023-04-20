package com.dacruz.starwarschallenge

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.dacruz.character.presentation.CharacterDetailScreen
import com.dacruz.character.presentation.CharacterScreen
import com.dacruz.favorites.presentation.FavoriteDetailScreen
import com.dacruz.favorites.presentation.FavoriteHomeScreen
import com.dacruz.home.presentation.HomeScreen
import com.dacruz.navigator.NavigationHandler
import com.dacruz.navigator.Screen
import com.dacruz.theme.Grey100
import com.dacruz.theme.Grey900
import kotlinx.coroutines.delay
import org.koin.compose.rememberKoinInject

@Composable
fun AppNavigator(navigationHandler: NavigationHandler = rememberKoinInject()) {
    val currentScreen by navigationHandler.currentScreen.collectAsState()

    when (currentScreen) {
        is Screen.SplashScreen -> SplashScreen(navigationHandler)
        is Screen.HomeScreen -> HomeScreen(navigationHandler)
        is Screen.CharacterScreen -> CharacterScreen(navigationHandler)
        is Screen.CharacterDetailScreen -> CharacterDetailScreen(navigationHandler)
        is Screen.FavoriteListScreen -> FavoriteHomeScreen(navigationHandler)
        is Screen.FavoriteDetailScreen -> FavoriteDetailScreen(navigationHandler)
    }
}

@Composable
fun SplashScreen(navigationHandler: NavigationHandler) {
    val alpha = remember { Animatable(1f) }

    LaunchedEffect(Unit) {
        delay(2000L)
        alpha.animateTo(0f, animationSpec = tween(durationMillis = 1000))
        navigationHandler.navigateTo(Screen.HomeScreen, pop = Screen.SplashScreen)
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Grey900)
        )
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Grey100,
            modifier = Modifier.alpha(alpha.value)
        )
    }
}
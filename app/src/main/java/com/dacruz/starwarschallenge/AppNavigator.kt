package com.dacruz.starwarschallenge

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.dacruz.home.HomeScreen
import com.dacruz.navigator.NavigationHandler
import com.dacruz.navigator.Screen
import org.koin.compose.koinInject
import org.koin.compose.rememberKoinInject

@Composable
fun AppNavigator() {
    val navigationHandler: NavigationHandler = koinInject()
    val currentScreen by navigationHandler.currentScreen.collectAsState()

    when (currentScreen) {
        is Screen.SplashScreen -> SplashScreen()
        is Screen.HomeScreen -> HomeScreen()
    }
}


@Composable
fun SplashScreen() {
    val navigationHandler: NavigationHandler = rememberKoinInject()

    Button(onClick = {
        navigationHandler.navigateTo(Screen.HomeScreen)
    }) {
        Text("Navigate to FeatureHomeScreen")
    }
}
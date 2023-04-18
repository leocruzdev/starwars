package com.dacruz.navigator

sealed class Screen {
    object SplashScreen : Screen()
    object HomeScreen : Screen()
    object CharacterScreen : Screen()
    object CharacterDetailScreen : Screen()
    object FavoriteListScreen : Screen()
    object FavoriteDetailScreen : Screen()
}
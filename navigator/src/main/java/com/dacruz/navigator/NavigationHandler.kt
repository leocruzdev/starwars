package com.dacruz.navigator

import kotlinx.coroutines.flow.StateFlow

interface NavigationHandler {
    val currentScreen: StateFlow<Screen>
    fun navigateTo(screen: Screen)
    fun navigateBack(onAppExit: () -> Unit = {})
}
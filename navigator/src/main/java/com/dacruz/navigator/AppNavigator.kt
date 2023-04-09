package com.dacruz.navigator

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.LinkedList

class AppNavigator : NavigationHandler {
    private val _currentScreen = MutableStateFlow<Screen>(Screen.SplashScreen)
    override val currentScreen: StateFlow<Screen> = _currentScreen

    private val screenHistory = LinkedList<Screen>().apply {
        push(Screen.SplashScreen)
    }

    override fun navigateTo(screen: Screen) {
        screenHistory.push(screen)
        _currentScreen.value = screen
    }

    override fun navigateBack(onAppExit: () -> Unit) {
        if (screenHistory.size > 1) {
            screenHistory.pop()
            _currentScreen.value = screenHistory.peek()
        } else {
            onAppExit()
        }
    }
}
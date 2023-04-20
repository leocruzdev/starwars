package com.dacruz.starwarschallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dacruz.navigator.NavigationHandler
import com.dacruz.theme.LocalIsLightTheme
import com.dacruz.theme.StarWarsTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val navigationHandler: NavigationHandler by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isLightTheme = LocalIsLightTheme.current
            StarWarsTheme(isDarkTheme = isLightTheme.value) {
                AppNavigator(navigationHandler)
            }
        }
    }

    override fun onBackPressed() {
        navigationHandler.navigateBack() { finish() }
    }
}
package com.dacruz.home

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.dacruz.navigator.NavigationHandler
import org.koin.compose.rememberKoinInject

@Composable
fun HomeScreen() {

    val navigationHandler: NavigationHandler = rememberKoinInject()
    Button(onClick = { navigationHandler.navigateBack() }) {
        Text("Navigate back")
    }
}
package com.dacruz.theme.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoadingButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    val isLoading = remember { mutableStateOf(false) }
    val isButtonVisible = remember { mutableStateOf(true) }
    val isContainerVisible = remember { mutableStateOf(true) }

    fun performClick() {
        if (!isLoading.value) {
            isButtonVisible.value = false // oculta o botão
            isContainerVisible.value = false // oculta o container
            isLoading.value = true
            coroutineScope.launch {
                delay(2000)
                onClick()
                isLoading.value = false
                isButtonVisible.value = true // mostra o botão
                isContainerVisible.value = true // mostra o container
            }
        }
    }

    ButtonContent(
        isLoading = isLoading.value,
        isButtonVisible = isButtonVisible.value,
        isContainerVisible = isContainerVisible.value,
        text = text,
        onClick = ::performClick,
        modifier = modifier
    )
}

@Composable
private fun ButtonContent(
    isLoading: Boolean,
    isButtonVisible: Boolean,
    isContainerVisible: Boolean,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
) {
    AnimatedVisibility(
        visible = isContainerVisible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            modifier = modifier.fillMaxWidth().height(56.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .heightIn(min = 56.dp) // define a altura mínima do container
            ) {
                if (isButtonVisible) { // mostra o botão se visível
                    Button(
                        onClick = onClick,
                        modifier = Modifier.fillMaxSize(),
                        enabled = !isLoading,
                        shape = RectangleShape
                    ) {
                        Text(text = text)
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = text,
                        modifier = Modifier.alpha(if (isButtonVisible) 1f else 0f) // define a transparência do texto
                    )

                    AnimatedLoadingIndicator(isLoading = isLoading)
                }
            }
        }
    }
}

@Composable
private fun AnimatedLoadingIndicator(isLoading: Boolean) {
    AnimatedVisibility(
        visible = isLoading,
        enter = fadeIn(animationSpec = tween(300)),
        exit = fadeOut(animationSpec = tween(300))
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier
                .size(20.dp)
                .padding(start = 4.dp),
            strokeWidth = 2.dp
        )
    }
}
package com.dacruz.theme.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign

@Composable
fun DefaultToolbar(
    title: String = "",
    textAlign: TextAlign = TextAlign.Start,
    onUpPress: () -> Unit = {},
    modifier: Modifier = Modifier,
    shouldShowNavigationIcon: Boolean = true,
    alternativeIcon: ImageVector? = null
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                textAlign = textAlign,
            )
        },
        navigationIcon = {
            if (alternativeIcon != null || shouldShowNavigationIcon) {
                IconButton(onClick = onUpPress) {
                    Icon(
                        imageVector = alternativeIcon ?: Icons.Rounded.ArrowBack,
                        contentDescription = "",
                    )
                }
            }
        },
        modifier = modifier,
    )
}
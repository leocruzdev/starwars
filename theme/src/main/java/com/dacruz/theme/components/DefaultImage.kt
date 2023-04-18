package com.dacruz.theme.components

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest.Builder
import coil.request.SuccessResult

@Composable
fun DefaultImage(
    bitmap: Bitmap? = null,
    imageUrl: String? = null,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
    getBitmapFromUrl: Boolean = false,
    onSuccess: (ImageBitmap) -> Unit = {},
) {
    if (bitmap != null) {
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = contentDescription,
            modifier = modifier,
            contentScale = contentScale,
        )
    } else if (imageUrl != null) {
        if (getBitmapFromUrl) {
            ImageWithBitmap(
                imageUrl = imageUrl,
                modifier = modifier,
                contentDescription = contentDescription,
                contentScale = contentScale,
                onSuccess = onSuccess
            )
        } else {
            ImageFromUrl(
                imageUrl = imageUrl,
                modifier = modifier,
                contentDescription = contentDescription,
                contentScale = contentScale
            )
        }
    }
}

@Composable
private fun ImageWithBitmap(
    imageUrl: String,
    modifier: Modifier,
    contentDescription: String?,
    contentScale: ContentScale,
    onSuccess: (ImageBitmap) -> Unit,
) {
    val context = LocalContext.current
    val painter = createAsyncImagePainter(context, imageUrl)
    obtainBitmapFromUrl(context, imageUrl, onSuccess)

    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale
    )
}

@Composable
private fun ImageFromUrl(
    imageUrl: String,
    modifier: Modifier,
    contentDescription: String?,
    contentScale: ContentScale,
) {
    val context = LocalContext.current
    val painter = createAsyncImagePainter(context, imageUrl)

    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale
    )
}

@Composable
private fun obtainBitmapFromUrl(
    context: Context,
    imageUrl: String,
    onSuccess: (ImageBitmap) -> Unit
) {
    LaunchedEffect(imageUrl) {
        val imageLoader = ImageLoader(context)
        val request = Builder(context)
            .data(imageUrl)
            .allowConversionToBitmap(true)
            .build()
        val result = imageLoader.execute(request)
        if (result is SuccessResult) {
            onSuccess(result.drawable.toBitmap().asImageBitmap())
        }
    }
}

@Composable
private fun createAsyncImagePainter(context: Context, imageUrl: String): AsyncImagePainter {
    val builder = Builder(context).data(data = imageUrl).apply(block = fun Builder.() {
        crossfade(true)
    }).build()
    return rememberAsyncImagePainter(builder)
}
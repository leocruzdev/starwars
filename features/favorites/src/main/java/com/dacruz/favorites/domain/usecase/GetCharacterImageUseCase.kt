package com.dacruz.favorites.domain.usecase

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.dacruz.favorites.domain.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetCharacterImageUseCase(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(url: String): Flow<Bitmap?> {
        return repository.getImageByUrl(url)
            .map { bytes ->
                bytes?.let { BitmapFactory.decodeByteArray(bytes, 0, it.size) }
            }
    }
}

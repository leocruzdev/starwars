package com.dacruz.favorites.data.local

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dacruz.database.FavoriteCharacterDao
import com.dacruz.database.model.FavoriteCharacterEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.min

class CharacterPagingSource(
    private val favoriteCharacterDao: FavoriteCharacterDao
) : PagingSource<Int, FavoriteCharacterEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FavoriteCharacterEntity> {
        return try {
            val pageIndex = params.key ?: 0
            val favoriteCharacters = withContext(Dispatchers.IO) {
                favoriteCharacterDao.getFavoriteCharacters()
            }

            val startPosition = pageIndex * params.loadSize
            val endPosition = min(startPosition + params.loadSize, favoriteCharacters.size)
            val pagedCharacters = favoriteCharacters.subList(startPosition, endPosition)

            if (pagedCharacters.size != params.loadSize && pagedCharacters.size != favoriteCharacters.size) {
                return LoadResult.Invalid()
            }
            LoadResult.Page(
                data = favoriteCharacters,
                prevKey = if (pageIndex == 0) null else pageIndex - params.loadSize,
                nextKey = if (favoriteCharacters.isEmpty()) null else pageIndex + params.loadSize
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, FavoriteCharacterEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val currentPage = state.closestPageToPosition(anchorPosition)
            currentPage?.prevKey?.plus(1) ?: currentPage?.nextKey?.minus(1)
        }
    }
}
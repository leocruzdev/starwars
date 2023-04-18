package com.dacruz.favorites.di

import androidx.paging.PagingSource
import com.dacruz.database.AppDatabase
import com.dacruz.database.model.FavoriteCharacterEntity
import com.dacruz.favorites.data.FavoriteRepositoryImpl
import com.dacruz.favorites.data.local.CharacterPagingSource
import com.dacruz.favorites.data.local.LocalDataSource
import com.dacruz.favorites.data.local.LocalDataSourceImpl
import com.dacruz.favorites.data.mapper.FavoriteCharacterDataMapper
import com.dacruz.favorites.domain.FavoriteRepository
import com.dacruz.favorites.domain.usecase.GetCharacterImageUseCase
import com.dacruz.favorites.domain.usecase.GetFavoriteCharactersUseCase
import com.dacruz.favorites.domain.usecase.RemoveFromFavoritesUseCase
import com.dacruz.favorites.presentation.FavoriteDetailViewModel
import com.dacruz.favorites.presentation.FavoriteHomeViewModel
import com.dacruz.favorites.presentation.mapper.FavoriteCharacterViewMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {

    single { get<AppDatabase>().favoriteCharacterDao() }
    single<FavoriteRepository> { FavoriteRepositoryImpl(localDataSource = get()) }

    single<LocalDataSource> { LocalDataSourceImpl(favoriteCharacterDao = get(), mapper = get()) }
    factory{
        CharacterPagingSource(favoriteCharacterDao= get())
    }
    single { GetFavoriteCharactersUseCase(repository = get()) }
    single { RemoveFromFavoritesUseCase(repository = get()) }
    single { GetCharacterImageUseCase(repository = get()) }

    factory { FavoriteCharacterDataMapper() }
    factory { FavoriteCharacterViewMapper() }

    viewModel {
        FavoriteHomeViewModel(
            favoriteCharacterViewMapper = get(),
            getFavoriteCharactersUseCase = get(),
        )
    }

    viewModel {
        FavoriteDetailViewModel(
            getImageFromDatabaseUseCase = get(),
            removeFromFavoritesUseCase = get()
         )
    }
}
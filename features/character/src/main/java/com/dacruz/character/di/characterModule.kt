package com.dacruz.character.di

import com.dacruz.character.data.CharacterRepositoryImpl
import com.dacruz.character.data.local.LocalDataSource
import com.dacruz.character.data.local.LocalDataSourceImpl
import com.dacruz.character.data.mapper.CharacterDataMapper
import com.dacruz.character.data.mapper.FavoriteCharacterDataMapper
import com.dacruz.character.data.remote.RemoteDataSource
import com.dacruz.character.data.remote.RemoteDataSourceImpl
import com.dacruz.character.data.service.CharacterService
import com.dacruz.character.domain.CharacterRepository
import com.dacruz.character.domain.usecase.AddToFavoritesUseCase
import com.dacruz.character.domain.usecase.GetCharacterImageUseCase
import com.dacruz.character.domain.usecase.GetCharactersUseCase
import com.dacruz.character.domain.usecase.IsCharacterFavoriteUseCase
import com.dacruz.character.domain.usecase.RemoveFromFavoritesUseCase
import com.dacruz.character.presentation.CharacterDetailViewModel
import com.dacruz.character.presentation.CharactersViewModel
import com.dacruz.character.presentation.mapper.CharacterViewMapper
import com.dacruz.character.presentation.mapper.FavoriteCharacteViewMapper
import com.dacruz.database.AppDatabase
import com.dacruz.networking.RetrofitBuilder
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val characterModule = module {

    single { RetrofitBuilder.createService(serviceClass = CharacterService::class) }
    single<RemoteDataSource> { RemoteDataSourceImpl(service = get(), mapper = get()) }
    single { get<AppDatabase>().favoriteCharacterDao() }
    single<CharacterRepository> {
        CharacterRepositoryImpl(
            remoteDataSource = get(),
            localDataSource = get()
        )
    }

    single<LocalDataSource> { LocalDataSourceImpl(favoriteCharacterDao = get(), mapper = get()) }

    single { GetCharactersUseCase(repository = get()) }
    single { AddToFavoritesUseCase(repository = get()) }
    single { RemoveFromFavoritesUseCase(repository = get()) }
    single { IsCharacterFavoriteUseCase(repository = get()) }
    single { GetCharacterImageUseCase(repository = get()) }


    // Mappers
    factory { CharacterDataMapper() }
    factory { CharacterViewMapper() }
    factory { FavoriteCharacterDataMapper() }
    factory { FavoriteCharacteViewMapper(characterViewMapper = get(), characterDataMapper = get()) }

    viewModel {
        CharactersViewModel(
            getCharactersUseCase = get(),
            characterViewMapper = get()
        )
    }

    viewModel {
        CharacterDetailViewModel(
            addToFavoritesUseCase = get(),
            favoriteCharacteViewMapper = get(),
            isCharacterFavoriteUseCase = get(),
            removeFromFavoritesUseCase = get()
        )
    }
}
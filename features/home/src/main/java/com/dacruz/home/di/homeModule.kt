package com.dacruz.home.di

import com.dacruz.home.data.HomeRepositoryImpl
import com.dacruz.home.data.service.HomeService
import com.dacruz.home.domain.HomeRepository
import com.dacruz.home.domain.usecase.GetCategoriesHome
import com.dacruz.home.presentation.HomeViewModel
import com.dacruz.networking.RetrofitBuilder
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import com.dacruz.home.data.mapper.CategoryMapper as DataCategoryMapper
import com.dacruz.home.presentation.mapper.CategoryMapper as ViewCategoryMapper


val homeModule = module {
    single { RetrofitBuilder.createService(serviceClass = HomeService::class) }
    singleOf(::HomeRepositoryImpl) bind HomeRepository::class
    factoryOf(::DataCategoryMapper)

    singleOf(::GetCategoriesHome)

    factoryOf(::ViewCategoryMapper)
    viewModelOf(::HomeViewModel)

}
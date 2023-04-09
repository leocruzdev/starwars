package com.dacruz.navigator.di

import com.dacruz.navigator.AppNavigator
import com.dacruz.navigator.NavigationHandler
import org.koin.dsl.module

val navigationModule = module {
    single<NavigationHandler> { AppNavigator() }
}
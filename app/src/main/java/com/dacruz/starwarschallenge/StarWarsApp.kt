package com.dacruz.starwarschallenge

import android.app.Application
import com.dacruz.navigator.di.navigationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class StarWarsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@StarWarsApp)
            modules(listOf(navigationModule))
        }
    }
}
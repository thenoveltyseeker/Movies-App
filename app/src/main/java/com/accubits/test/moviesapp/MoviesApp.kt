package com.accubits.test.moviesapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * @author Rolbin, created on 08-01-2021.
 */
class MoviesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MoviesApp)
            modules(listOf(networkModule, moviesModule))
        }
    }
}
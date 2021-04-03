package ru.chernakov.sampler.coredata.app.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.chernakov.sampler.coredata.prefs.SharedPreferencesProvider

val dataModule = module {
    single { SharedPreferencesProvider.provide(androidContext()) }
}
package ru.chernakov.sampler.coredata.app.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.chernakov.sampler.coredata.prefs.AppSharedPreferences
import ru.chernakov.sampler.coredata.prefs.SharedPreferencesApi

val dataModule = module {
    single<SharedPreferencesApi> { AppSharedPreferences(androidContext()) }
}
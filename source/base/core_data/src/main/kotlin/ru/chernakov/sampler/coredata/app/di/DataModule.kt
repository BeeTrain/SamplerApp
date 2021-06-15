package ru.chernakov.sampler.coredata.app.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.chernakov.sampler.coredata.storage.SimpleDataStorageProvider

val dataModule = module {
    single { SimpleDataStorageProvider.provide(androidContext()) }
}
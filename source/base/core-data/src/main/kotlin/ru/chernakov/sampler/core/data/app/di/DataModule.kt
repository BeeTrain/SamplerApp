package ru.chernakov.sampler.core.data.app.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.chernakov.sampler.core.data.storage.SimpleDataStorageProvider

val dataModule = module {

    single { SimpleDataStorageProvider.provide(androidContext()) }
}
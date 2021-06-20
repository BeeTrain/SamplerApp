package ru.chernakov.sampler.core.data.storage

import android.content.Context

object SimpleDataStorageProvider {

    fun provide(applicationContext: Context): SimpleDataStorage {
        return PreferencesSimpleDataStorage(applicationContext)
    }
}
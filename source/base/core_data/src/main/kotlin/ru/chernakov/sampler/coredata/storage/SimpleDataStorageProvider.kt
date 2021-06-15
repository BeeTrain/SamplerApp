package ru.chernakov.sampler.coredata.storage

import android.content.Context

object SimpleDataStorageProvider {

    fun provide(applicationContext: Context): SimpleDataStorage {
        return PreferencesSimpleDataStorage(applicationContext)
    }
}
package ru.chernakov.sampler.coredata.prefs

import android.content.Context

class SharedPreferencesProvider {

    fun provide(applicationContext: Context): SharedPreferencesApi {
        return AppSharedPreferences(applicationContext)
    }
}
package ru.chernakov.sampler.coredata.prefs

import android.content.Context

object SharedPreferencesProvider {

    fun provide(applicationContext: Context): SharedPreferencesApi {
        return AppSharedPreferences(applicationContext)
    }
}
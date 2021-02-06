package ru.chernakov.sampler.coredata.prefs

import android.content.SharedPreferences

interface SharedPreferencesApi {

    // put's

    fun putString(key: String, value: String)
    fun putNullableString(key: String, value: String?)
    fun putStringSet(key: String, value: Set<String>)
    fun putNullableStringSet(key: String, value: Set<String>?)
    fun putBoolean(key: String, value: Boolean)
    fun putInt(key: String, value: Int)
    fun putLong(key: String, value: Long)
    fun putFloat(key: String, value: Float)

    // get's

    fun getString(key: String): String
    fun getNullableString(key: String): String?
    fun getStringSet(key: String): Set<String>
    fun getNullableStringSet(key: String): Set<String>?
    fun getBoolean(key: String): Boolean
    fun getNullableBoolean(key: String): Boolean?
    fun getInt(key: String): Int
    fun getNullableInt(key: String): Int?
    fun getLong(key: String): Long
    fun getNullableLong(key: String): Long?
    fun getFloat(key: String): Float
    fun getNullableFloat(key: String): Float?

    // other

    fun remove(key: String)
    fun registerChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener)
}
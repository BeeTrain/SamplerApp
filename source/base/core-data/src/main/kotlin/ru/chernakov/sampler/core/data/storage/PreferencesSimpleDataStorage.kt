package ru.chernakov.sampler.core.data.storage

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

@Suppress("TooManyFunctions")
class PreferencesSimpleDataStorage(applicationContext: Context) : SimpleDataStorage {

    private val preferences = applicationContext.getSharedPreferences(PREFS_FILE_NAME, MODE_PRIVATE)

    private val editor: SharedPreferences.Editor
        get() = preferences.edit()

    override fun putString(key: String, value: String) {
        editor.putString(key, value).apply()
    }

    override fun putNullableString(key: String, value: String?) {
        editor.putString(key, value).apply()
    }

    override fun putStringSet(key: String, value: Set<String>) {
        editor.putStringSet(key, value).apply()
    }

    override fun putNullableStringSet(key: String, value: Set<String>?) {
        editor.putStringSet(key, value).apply()
    }

    override fun putBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value).apply()
    }

    override fun putInt(key: String, value: Int) {
        editor.putInt(key, value).apply()
    }

    override fun putLong(key: String, value: Long) {
        editor.putLong(key, value).apply()
    }

    override fun putFloat(key: String, value: Float) {
        editor.putFloat(key, value).apply()
    }

    override fun getString(key: String): String {
        return preferences.getString(key, null) ?: throw IllegalStateException(NULL_VALUE_MESSAGE)
    }

    override fun getNullableString(key: String): String? {
        return preferences.getString(key, null)
    }

    override fun getStringSet(key: String): Set<String> {
        return preferences.getStringSet(key, null) ?: throw IllegalStateException(NULL_VALUE_MESSAGE)
    }

    override fun getNullableStringSet(key: String): Set<String>? {
        return preferences.getStringSet(key, null)
    }

    override fun getBoolean(key: String): Boolean {
        if (preferences.contains(key).not()) throw IllegalStateException(NULL_VALUE_MESSAGE)

        return preferences.getBoolean(key, false)
    }

    override fun getNullableBoolean(key: String): Boolean? {
        return preferences.getBoolean(key, false).takeIf { preferences.contains(key) }
    }

    override fun getInt(key: String): Int {
        if (preferences.contains(key).not()) throw IllegalStateException(NULL_VALUE_MESSAGE)

        return preferences.getInt(key, 0)
    }

    override fun getNullableInt(key: String): Int? {
        return preferences.getInt(key, 0).takeIf { preferences.contains(key) }
    }

    override fun getLong(key: String): Long {
        if (preferences.contains(key).not()) throw IllegalStateException(NULL_VALUE_MESSAGE)

        return preferences.getLong(key, 0)
    }

    override fun getNullableLong(key: String): Long? {
        return preferences.getLong(key, 0).takeIf { preferences.contains(key) }
    }

    override fun getFloat(key: String): Float {
        if (preferences.contains(key).not()) throw IllegalStateException(NULL_VALUE_MESSAGE)

        return preferences.getFloat(key, 0F)
    }

    override fun getNullableFloat(key: String): Float? {
        return preferences.getFloat(key, 0F).takeIf { preferences.contains(key) }
    }

    override fun remove(key: String) {
        editor.remove(key)
    }

    companion object {
        private const val NULL_VALUE_MESSAGE = "returned value is null"
        private const val PREFS_FILE_NAME = "SamplerApp-SimpleDataStorage"
    }
}
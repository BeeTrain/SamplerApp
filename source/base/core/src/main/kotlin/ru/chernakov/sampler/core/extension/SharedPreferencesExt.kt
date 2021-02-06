package ru.chernakov.sampler.core.extension

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun SharedPreferences.string(
    defaultValue: String = "",
    key: (KProperty<*>) -> String = KProperty<*>::name
): ReadWriteProperty<Any, String> {
    return object : ReadWriteProperty<Any, String> {
        override fun getValue(thisRef: Any, property: KProperty<*>): String {
            return getString(key(property), defaultValue) ?: defaultValue
        }

        override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
            edit().putString(key(property), value).apply()
        }
    }
}

fun SharedPreferences.stringNullable(
    defaultValue: String? = null,
    key: (KProperty<*>) -> String = KProperty<*>::name
): ReadWriteProperty<Any, String?> {
    return object : ReadWriteProperty<Any, String?> {
        override fun getValue(thisRef: Any, property: KProperty<*>): String? {
            return getString(key(property), defaultValue) ?: defaultValue
        }

        override fun setValue(thisRef: Any, property: KProperty<*>, value: String?) {
            edit().putString(key(property), value).apply()
        }
    }
}

fun SharedPreferences.int(
    defaultValue: Int = 0,
    key: (KProperty<*>) -> String = KProperty<*>::name
): ReadWriteProperty<Any, Int> {
    return object : ReadWriteProperty<Any, Int> {
        override fun getValue(thisRef: Any, property: KProperty<*>): Int {
            return getInt(key(property), defaultValue)
        }

        override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) {
            edit().putInt(key(property), value).apply()
        }
    }
}
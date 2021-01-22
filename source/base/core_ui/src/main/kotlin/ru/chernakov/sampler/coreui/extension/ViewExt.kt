package ru.chernakov.sampler.coreui.extension

import android.view.View

inline fun <reified T : View> View.accessField(fieldName: String): T? {
    val field = this::class.java.getDeclaredField(fieldName)
    field.isAccessible = true
    return field.get(this) as? T
}
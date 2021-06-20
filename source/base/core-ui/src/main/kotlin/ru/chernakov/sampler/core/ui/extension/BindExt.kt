package ru.chernakov.sampler.core.ui.extension

import android.app.Activity
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

inline fun <reified T : Any?> View.bind(@IdRes idRes: Int): Lazy<T> {
    return unsafeLazy { findViewById<View>(idRes) as T }
}

inline fun <reified T : Any?> Fragment.bind(@IdRes idRes: Int): Lazy<T> {
    return unsafeLazy { view?.findViewById<View>(idRes) as T }
}

inline fun <reified T : Any?> Activity.bind(@IdRes idRes: Int): Lazy<T> {
    return unsafeLazy { findViewById<View>(idRes) as T }
}

fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)
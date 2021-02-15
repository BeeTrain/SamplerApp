package ru.chernakov.sampler.coreui.extension

import android.os.Handler
import android.os.Looper

private const val DEFAULT_DELAY = 300L

fun doPostDelayed(delay: Long = DEFAULT_DELAY, looper: Looper = Looper.getMainLooper(), action: (() -> Unit)) {
    Handler(looper).postDelayed(action, delay)
}
package ru.chernakov.sampler.coreui.extension

import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.DecelerateInterpolator

private const val DEFAULT_DURATION = 500L

fun View.runFadeInAnimation(animationDuration: Long = DEFAULT_DURATION) {
    animation = AlphaAnimation(0f, 1f).apply {
        interpolator = DecelerateInterpolator()
        duration = animationDuration
    }
    animate()
}
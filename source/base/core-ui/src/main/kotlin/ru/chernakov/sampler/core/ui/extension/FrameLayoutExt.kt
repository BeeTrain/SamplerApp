package ru.chernakov.sampler.core.ui.extension

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.StateListDrawable
import android.util.TypedValue
import android.widget.FrameLayout
import androidx.annotation.IntRange
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import ru.chernakov.sampler.R

fun FrameLayout.setSelectableForeground(
    @IntRange(from = 0, to = 255) overlayAlpha: Int = 0
) {
    val layoutBackground = background
    val overlay = when (layoutBackground is ColorDrawable) {
        true -> ColorDrawable(ColorUtils.setAlphaComponent(layoutBackground.color, overlayAlpha))
        else -> null
    }
    val selectableResId = TypedValue().run {
        context.theme.resolveAttribute(R.attr.selectableItemBackground, this, true)
        resourceId
    }

    val selectable = ContextCompat.getDrawable(context, selectableResId)
    val stateList = StateListDrawable()
    stateList.addState(intArrayOf(-android.R.attr.state_enabled), overlay)
    stateList.addState(intArrayOf(android.R.attr.state_enabled), selectable)

    foreground = stateList
}
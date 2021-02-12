package ru.chernakov.sampler.coreui.extension

import android.content.res.Resources
import android.util.TypedValue

private const val DP_DENSITY = 160f

fun pxToDp(px: Float): Float = px / (Resources.getSystem().displayMetrics.densityDpi.toFloat() / DP_DENSITY)

fun dpToPx(dp: Float): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().displayMetrics).toInt()
}

fun spToPx(sp: Float): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, Resources.getSystem().displayMetrics).toInt()
}
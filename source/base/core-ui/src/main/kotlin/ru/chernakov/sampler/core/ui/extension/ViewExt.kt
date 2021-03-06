package ru.chernakov.sampler.core.ui.extension

import android.graphics.Rect
import android.os.SystemClock
import android.util.TypedValue
import android.view.View
import android.view.animation.AnimationUtils
import androidx.annotation.AnimRes
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

const val DEFAULT_TIME_OUT = 350L

fun View.doOnApplyWindowInsets(block: (View, insets: WindowInsetsCompat, initialPadding: Rect) -> WindowInsetsCompat) {
    val initialPadding = this.recordInitialPadding()
    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        block(v, insets, initialPadding)
    }
    requestApplyInsetsWhenAttached()
}

fun View.recordInitialPadding() = Rect(this.paddingLeft, this.paddingTop, this.paddingRight, this.paddingBottom)

private fun View.requestApplyInsetsWhenAttached() {
    if (isAttachedToWindow) {
        ViewCompat.requestApplyInsets(this)
    } else {
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                v.removeOnAttachStateChangeListener(this)
                ViewCompat.requestApplyInsets(v)
            }

            override fun onViewDetachedFromWindow(v: View) = Unit
        })
    }
}

@ColorInt
fun View.getAttrColor(@AttrRes attr: Int) = context.getAttrColor(attr)

fun View.getAttrResId(@AttrRes attr: Int) = context.getAttrResId(attr)

fun View.startAnimation(@AnimRes anim: Int) = startAnimation(AnimationUtils.loadAnimation(context, anim))

fun View.setOnClickWithDelayListener(delayMs: Long = DEFAULT_TIME_OUT, action: ((View) -> Unit)?) {
    isClickable = if (action != null) {
        setOnClickListener(getOnClickWithDelayListener(delayMs, action))
        true
    } else {
        setOnClickListener(null)
        false
    }
}

fun View.setClickableExt(clickable: Boolean) {
    this.apply {
        isClickable = clickable
        isFocusable = clickable

        if (clickable) {
            addRipple()
        } else {
            setBackgroundResource(0)
        }
    }
}

fun View.addRipple() = with(TypedValue()) {
    context.theme.resolveAttribute(android.R.attr.selectableItemBackground, this, true)
    setBackgroundResource(resourceId)
}

fun View.addCircleRipple() = with(TypedValue()) {
    context.theme.resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, this, true)
    setBackgroundResource(resourceId)
}

private fun getOnClickWithDelayListener(delayMs: Long, action: (View) -> Unit): View.OnClickListener {
    return object : View.OnClickListener {

        private var lastClickTime = 0L

        override fun onClick(v: View) {
            val currentTime = SystemClock.uptimeMillis()
            if (lastClickTime == 0L || currentTime - lastClickTime > delayMs) {
                lastClickTime = currentTime
                action(v)
            }
        }
    }
}
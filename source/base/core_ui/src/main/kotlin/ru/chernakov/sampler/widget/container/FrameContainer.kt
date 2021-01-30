package ru.chernakov.sampler.widget.container

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import ru.chernakov.sampler.R
import ru.chernakov.sampler.coreui.extension.getAttrColor

class FrameContainer
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    init {
        applyAppTheme()
    }

    private fun applyAppTheme() {
        setBackgroundColor(context.getAttrColor(R.attr.colorBackground))
    }
}
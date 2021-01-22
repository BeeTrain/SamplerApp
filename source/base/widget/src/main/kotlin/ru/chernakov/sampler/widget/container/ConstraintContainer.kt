package ru.chernakov.sampler.widget.container

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import ru.chernakov.sampler.coreui.extension.getAttrColor
import ru.chernakov.sampler.widget.R

class ConstraintContainer
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    init {
        applyAppTheme()
    }

    private fun applyAppTheme() {
        setBackgroundColor(context.getAttrColor(R.attr.colorBackground))
    }
}
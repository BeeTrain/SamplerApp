package ru.chernakov.sampler.widget.navigation

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.chernakov.sampler.R
import ru.chernakov.sampler.coreui.extension.getAttrColor


class BottomNavBar
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BottomNavigationView(context, attrs, defStyleAttr) {
    init {
        applyAppTheme()
    }

    private fun applyAppTheme() {
        val surfaceColor = context.getAttrColor(R.attr.colorSurface)
        val onSurfaceColor = context.getAttrColor(R.attr.colorOnSurface)
        val highlightColor = context.getAttrColor(R.attr.colorPrimary)
        val itemColorList = ColorStateList(
            arrayOf(
                intArrayOf(-android.R.attr.state_checked),
                intArrayOf(android.R.attr.state_checked)
            ),
            intArrayOf(
                onSurfaceColor,
                highlightColor
            )
        )

        setBackgroundColor(surfaceColor)
        itemTextColor = itemColorList
    }
}
package ru.chernakov.sampler.widget.appbar

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import com.google.android.material.appbar.MaterialToolbar
import ru.chernakov.sampler.coreui.extension.getAttrColor
import ru.chernakov.sampler.coreui.extension.getDrawableExt
import ru.chernakov.sampler.widget.R

class Toolbar
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : MaterialToolbar(context, attrs) {
    init {
        applyAppTheme()
    }

    private fun applyAppTheme() {
        setBackgroundColor(context.getAttrColor(R.attr.colorSurface))
        setTitleTextColor(context.getAttrColor(R.attr.colorOnSurface))
        setSubtitleTextColor(context.getAttrColor(R.attr.colorOnSurface))
    }

    override fun setNavigationIcon(resId: Int) {
        val icon = context.getDrawableExt(resId)
        setNavigationIcon(icon)
    }

    override fun setNavigationIcon(icon: Drawable?) {
        val themedIcon = icon?.apply { setTint(context.getAttrColor(R.attr.colorOnSurface)) }
        super.setNavigationIcon(themedIcon)
    }
}
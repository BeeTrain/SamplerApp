package ru.chernakov.sampler.widget.text

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import ru.chernakov.sampler.coreui.extension.getAttrResId
import ru.chernakov.sampler.coreui.extension.setTextAppearanceExt
import ru.chernakov.sampler.widget.R

class TextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatTextView(context, attrs) {
    init {
        attrs?.let { applyAttributes(it) }
    }

    private fun applyAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextView)
        try {
            val type = Type.values()[typedArray.getInteger(R.styleable.TextView_textType, Type.NONE.ordinal)]
            if (type != Type.NONE) {
                applyTextType(type)
            }
        } finally {
            typedArray.recycle()
        }
    }

    private fun applyTextType(type: Type) {
        val textAppearanceAttr = when (type) {
            Type.NONE -> null
            Type.HEADLINE_1 -> R.attr.styleAppearanceHeadline1
            Type.HEADLINE_2 -> R.attr.styleAppearanceHeadline2
            Type.HEADLINE_3 -> R.attr.styleAppearanceHeadline3
            Type.HEADLINE_4 -> R.attr.styleAppearanceHeadline4
            Type.HEADLINE_5 -> R.attr.styleAppearanceHeadline5
            Type.HEADLINE_6 -> R.attr.styleAppearanceHeadline6
            Type.SUBTITLE_1 -> R.attr.styleAppearanceSubtitle1
            Type.SUBTITLE_2 -> R.attr.styleAppearanceSubtitle2
            Type.BODY_1 -> R.attr.styleAppearanceBody1
            Type.BODY_2 -> R.attr.styleAppearanceBody2
            Type.BUTTON -> R.attr.styleAppearanceButton
            Type.CAPTION -> R.attr.styleAppearanceCaption
            Type.OVERLINE -> R.attr.styleAppearanceOverline
        }
        textAppearanceAttr?.let { setTextAppearanceExt(context.getAttrResId(it)) }
    }
}
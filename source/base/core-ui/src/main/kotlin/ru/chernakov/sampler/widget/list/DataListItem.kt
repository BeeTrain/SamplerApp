package ru.chernakov.sampler.widget.list

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.view.isVisible
import ru.chernakov.sampler.R
import ru.chernakov.sampler.core.ui.extension.bind
import ru.chernakov.sampler.widget.container.constraint.ConstraintContainer
import ru.chernakov.sampler.widget.image.ImageView
import ru.chernakov.sampler.widget.text.TextView

class DataListItem
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val dataListRoot by bind<ConstraintContainer>(R.id.data_list_item_root)
    private val dataListIcon by bind<ImageView>(R.id.data_list_item_icon)
    private val dataListTitle by bind<TextView>(R.id.data_list_item_title)

    var icon: Drawable? = null
        set(value) {
            field = value
            dataListIcon.setImageDrawable(value)
            dataListIcon.isVisible = value != null
        }

    var title: String? = null
        set(value) {
            field = value
            val isVisible = !value.isNullOrEmpty()
            dataListTitle.text = value ?: ""
            dataListTitle.isVisible = isVisible
        }

    init {
        inflate(context, R.layout.data_list_item, this)
        attrs?.let { applyAttributes(it) }
    }

    private fun applyAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.DataListItem)
        try {
            icon = typedArray.getDrawable(R.styleable.DataListItem_icon)
            title = typedArray.getString(R.styleable.DataListItem_title)
        } finally {
            typedArray.recycle()
        }
    }
}
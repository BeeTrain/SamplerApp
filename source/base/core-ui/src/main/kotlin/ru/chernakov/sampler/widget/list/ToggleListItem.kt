package ru.chernakov.sampler.widget.list

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import ru.chernakov.sampler.R
import ru.chernakov.sampler.core.ui.extension.bind
import ru.chernakov.sampler.core.ui.extension.setClickableExt
import ru.chernakov.sampler.widget.button.SwitchButton
import ru.chernakov.sampler.widget.container.constraint.ConstraintContainer
import ru.chernakov.sampler.widget.image.ImageView
import ru.chernakov.sampler.widget.text.TextView

class ToggleListItem
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val toggleListRoot by bind<ConstraintContainer>(R.id.toggle_list_item_root)
    private val toggleListIcon by bind<ImageView>(R.id.toggle_list_item_icon)
    private val toggleListTitle by bind<TextView>(R.id.toggle_list_item_title)
    private val toggleListLabel by bind<TextView>(R.id.toggle_list_item_label)
    private val toggleListCaption by bind<TextView>(R.id.toggle_list_item_caption)
    private val toggleListSwitch by bind<SwitchButton>(R.id.toggle_list_item_switch)

    var checkedChangeListener: ((View?, Boolean) -> Unit)? = null
        set(value) {
            field = value
            toggleListSwitch.setOnCheckedChangeListener(field)

            val isClickable = value != null
            toggleListSwitch.isEnabled = isClickable
            toggleListRoot.setClickableExt(isClickable)
            toggleListRoot.setOnClickListener {
                if (isClickable) toggleListSwitch.performClick()
            }
        }

    var icon: Drawable? = null
        set(value) {
            field = value
            toggleListIcon.setImageDrawable(value)
            toggleListIcon.isVisible = value != null
        }

    var title: String? = null
        set(value) {
            field = value
            val isVisible = !value.isNullOrEmpty()
            toggleListTitle.text = value ?: ""
            toggleListTitle.isVisible = isVisible
            updateViewsParams()
        }

    var label: String? = null
        set(value) {
            field = value
            toggleListLabel.text = value ?: ""
            toggleListLabel.isVisible = !value.isNullOrEmpty()
            updateViewsParams()
        }

    var caption: String? = null
        set(value) {
            field = value
            toggleListCaption.text = value ?: ""
            toggleListCaption.isVisible = !value.isNullOrEmpty()
            updateViewsParams()
        }

    var isChecked = false
        get() = toggleListSwitch.isChecked
        set(value) {
            field = value
            toggleListSwitch.isChecked = value
        }

    init {
        inflate(context, R.layout.toggle_list_item, this)
        attrs?.let { applyAttributes(it) }
    }

    private fun applyAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ToggleListItem)
        try {
            icon = typedArray.getDrawable(R.styleable.ToggleListItem_icon)
            title = typedArray.getString(R.styleable.ToggleListItem_title)
            label = typedArray.getString(R.styleable.ToggleListItem_label)
            caption = typedArray.getString(R.styleable.ToggleListItem_caption)
            isChecked = typedArray.getBoolean(R.styleable.ToggleListItem_isChecked, isChecked)
        } finally {
            typedArray.recycle()
        }
    }

    private fun updateViewsParams() {
        val isThreeLine = !title.isNullOrEmpty() && !label.isNullOrEmpty() && !caption.isNullOrEmpty()

        toggleListIcon.updateLayoutParams<ConstraintLayout.LayoutParams> {
            verticalBias = if (isThreeLine) 0F else 0.5F
            topToTop = if (isThreeLine) toggleListTitle.id else toggleListRoot.id
        }
        toggleListSwitch.updateLayoutParams<ConstraintLayout.LayoutParams> {
            topToTop = if (isThreeLine) toggleListLabel.id else toggleListRoot.id
            bottomToBottom = if (isThreeLine) toggleListCaption.id else toggleListRoot.id
        }
    }
}
package ru.chernakov.sampler.widget.list

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.isVisible
import ru.chernakov.sampler.R
import ru.chernakov.sampler.coreui.extension.bind
import ru.chernakov.sampler.widget.button.SwitchButton
import ru.chernakov.sampler.widget.container.ConstraintContainer
import ru.chernakov.sampler.widget.text.TextView

class ToggleListItem
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val toggleListRoot by bind<ConstraintContainer>(R.id.toggle_list_item_root)
    private val toggleListTitle by bind<TextView>(R.id.toggle_list_item_title)
    private val toggleListSubtitle by bind<TextView>(R.id.toggle_list_item_subtitle)
    private val toggleListSwitch by bind<SwitchButton>(R.id.toggle_list_item_switch)

    var checkedChangeListener: ((View?, Boolean) -> Unit)? = null
        set(value) {
            field = value
            toggleListSwitch.setOnCheckedChangeListener(field)

            if (value != null) {
                toggleListRoot.setOnClickListener { toggleListSwitch.performClick() }
            } else {
                toggleListRoot.setOnClickListener(null)
            }
        }

    var title: String? = null
        set(value) {
            field = value
            toggleListTitle.text = value ?: ""
            toggleListTitle.isVisible = value != null
        }

    var subtitle: String? = null
        set(value) {
            field = value
            toggleListSubtitle.text = value ?: ""
            toggleListSubtitle.isVisible = value != null
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
            title = typedArray.getString(R.styleable.ToggleListItem_title)
            subtitle = typedArray.getString(R.styleable.ToggleListItem_subtitle)
            isChecked = typedArray.getBoolean(R.styleable.ToggleListItem_isChecked, isChecked)
        } finally {
            typedArray.recycle()
        }
    }
}
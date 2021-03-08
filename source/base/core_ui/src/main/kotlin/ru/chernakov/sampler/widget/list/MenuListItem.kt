package ru.chernakov.sampler.widget.list

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.PopupMenu
import androidx.annotation.MenuRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import ru.chernakov.sampler.R
import ru.chernakov.sampler.coreui.extension.bind
import ru.chernakov.sampler.coreui.extension.setClickableExt
import ru.chernakov.sampler.widget.container.constraint.ConstraintContainer
import ru.chernakov.sampler.widget.image.ImageView
import ru.chernakov.sampler.widget.text.TextView

class MenuListItem
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val menuListRoot by bind<ConstraintContainer>(R.id.menu_list_item_root)
    private val menuListIcon by bind<ImageView>(R.id.menu_list_item_icon)
    private val menuListTitle by bind<TextView>(R.id.menu_list_item_title)
    private val menuListLabel by bind<TextView>(R.id.menu_list_item_label)
    private val menuListCaption by bind<TextView>(R.id.menu_list_item_caption)
    private val menuListMenuIcon by bind<ImageView>(R.id.menu_list_item_menu)

    var icon: Drawable? = null
        set(value) {
            field = value
            menuListIcon.setImageDrawable(value)
            menuListIcon.isVisible = value != null
        }

    var title: String? = null
        set(value) {
            field = value
            val isVisible = !value.isNullOrEmpty()
            menuListTitle.text = value ?: ""
            menuListTitle.isVisible = isVisible
            updateViewsParams()
        }

    var label: String? = null
        set(value) {
            field = value
            menuListLabel.text = value ?: ""
            menuListLabel.isVisible = !value.isNullOrEmpty()
            updateViewsParams()
        }

    var caption: String? = null
        set(value) {
            field = value
            menuListCaption.text = value ?: ""
            menuListCaption.isVisible = !value.isNullOrEmpty()
            updateViewsParams()
        }

    @MenuRes
    var menu: Int? = null
        set(value) {
            field = value
            menuListMenuIcon.isVisible = value != null
        }

    var menuActionClickListener: ((Int) -> Unit)? = null
        set(value) {
            field = value

            val isClickable = value != null
            menuListMenuIcon.setClickableExt(isClickable)
            menuListMenuIcon.setOnClickListener { if (isClickable) showMenu() }
        }

    init {
        inflate(context, R.layout.menu_list_item, this)
        attrs?.let { applyAttributes(it) }
    }

    private fun applyAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MenuListItem)
        try {
            icon = typedArray.getDrawable(R.styleable.MenuListItem_icon)
            title = typedArray.getString(R.styleable.MenuListItem_title)
            label = typedArray.getString(R.styleable.MenuListItem_label)
            caption = typedArray.getString(R.styleable.MenuListItem_caption)
            menu = typedArray.getResourceId(R.styleable.MenuListItem_menu, 0).takeIf { it != 0 }

        } finally {
            typedArray.recycle()
        }
    }

    private fun showMenu() {
        val menuRes = menu ?: return

        with(menuListMenuIcon) {
            PopupMenu(context, this).apply {
                menuInflater.inflate(menuRes, menu)
                setOnMenuItemClickListener {
                    menuActionClickListener?.invoke(it.itemId)
                    true
                }
                show()
            }
        }
    }

    private fun updateViewsParams() {
        val isThreeLine = !title.isNullOrEmpty() && !label.isNullOrEmpty() && !caption.isNullOrEmpty()

        menuListIcon.updateLayoutParams<ConstraintLayout.LayoutParams> {
            verticalBias = if (isThreeLine) 0F else 0.5F
            topToTop = if (isThreeLine) menuListTitle.id else menuListRoot.id
        }
    }
}
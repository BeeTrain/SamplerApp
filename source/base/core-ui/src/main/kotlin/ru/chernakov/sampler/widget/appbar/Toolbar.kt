package ru.chernakov.sampler.widget.appbar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import android.view.WindowInsets
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.updatePadding
import com.google.android.material.appbar.MaterialToolbar
import kotlin.math.max
import ru.chernakov.sampler.R
import ru.chernakov.sampler.core.ui.extension.dpToPx
import ru.chernakov.sampler.core.ui.extension.getAttrColor
import ru.chernakov.sampler.core.ui.extension.getDrawableExt
import ru.chernakov.sampler.core.ui.extension.unsafeLazy

class Toolbar
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : MaterialToolbar(context, attrs) {

    private val dividerPaint by unsafeLazy {
        Paint().apply {
            strokeWidth = dpToPx(1F).toFloat()
            color = context.getAttrColor(R.attr.colorOnSurface)
        }
    }

    private val titleTextBoundsRect by unsafeLazy { Rect() }

    private val titleView by unsafeLazy { accessField<TextView>(TITLE_TEXT_VIEW_FIELD) }
    private val subTitleView by unsafeLazy { accessField<TextView>(SUB_TITLE_TEXT_VIEW_FIELD) }

    init {
        applyAppTheme()
    }

    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)
        canvas.drawLine(0F, height.toFloat(), width.toFloat(), height.toFloat(), dividerPaint)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        marginViews()
        setTitleEllipsize()
        setSubtitleEllipsize()
    }

    override fun setNavigationIcon(resId: Int) {
        val icon = context.getDrawableExt(resId)
        setNavigationIcon(icon)
    }

    override fun setNavigationIcon(icon: Drawable?) {
        val themedIcon = icon?.apply { setTint(context.getAttrColor(R.attr.colorOnSurface)) }
        super.setNavigationIcon(themedIcon)
    }

    override fun onApplyWindowInsets(insets: WindowInsets): WindowInsets {
        updatePadding(top = insets.systemWindowInsetTop)
        return insets
    }

    private fun applyAppTheme() {
        setBackgroundColor(context.getAttrColor(R.attr.colorSurface))
        setTitleTextColor(context.getAttrColor(R.attr.colorOnSurface))
        setSubtitleTextColor(context.getAttrColor(R.attr.colorOnSurface))
        setNavigationIconTint(context.getAttrColor(R.attr.colorOnSurface))
    }

    private fun marginViews() {
        titleView?.let { translateTextView(it) }
        subTitleView?.let { translateTextView(it) }
    }

    private fun translateTextView(textView: TextView) {
        val titleWidth = textView.width.takeIf { it != 0 } ?: calculateTextViewWidth(textView)
        val titleCentralizedStart = max(width / 2 - titleWidth / 2, 0)
        textView.x = titleCentralizedStart.toFloat()
    }

    private fun calculateTextViewWidth(title: TextView): Int {
        val paint = TextPaint().apply {
            textSize = title.textSize
            typeface = title.typeface
        }
        paint.getTextBounds(title.text.toString(), 0, title.text.length, titleTextBoundsRect)
        return titleTextBoundsRect.width()
    }

    private fun setTitleEllipsize() {
        titleView?.let {
            it.ellipsize = TextUtils.TruncateAt.END
            it.letterSpacing = 0f
        }
    }

    private fun setSubtitleEllipsize() {
        subTitleView?.let {
            it.ellipsize = TextUtils.TruncateAt.END
            it.letterSpacing = 0f
        }
    }

    private inline fun <reified T : View> accessField(fieldName: String): T? {
        val field = Toolbar::class.java.getDeclaredField(fieldName)
        field.isAccessible = true
        return field.get(this) as? T
    }

    companion object {
        private const val TITLE_TEXT_VIEW_FIELD = "mTitleTextView"
        private const val SUB_TITLE_TEXT_VIEW_FIELD = "mSubtitleTextView"
    }
}
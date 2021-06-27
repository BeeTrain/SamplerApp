package ru.chernakov.sampler.widget.card

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.card.MaterialCardView

open class CardView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr)
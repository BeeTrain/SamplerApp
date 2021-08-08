package ru.chernakov.sampler.main.services.presentation.adapter

import android.view.View
import android.widget.FrameLayout
import androidx.core.view.isVisible
import ru.chernakov.sampler.core.ui.extension.bind
import ru.chernakov.sampler.core.ui.extension.setOnClickWithDelayListener
import ru.chernakov.sampler.core.ui.extension.setSelectableForeground
import ru.chernakov.sampler.main.services.R
import ru.chernakov.sampler.main.services.data.model.Service
import ru.chernakov.sampler.widget.image.ImageView
import ru.chernakov.sampler.widget.list.ViewHolder
import ru.chernakov.sampler.widget.text.TextView

class ServicesViewHolder(
    itemView: View,
    onClick: ((Service) -> Unit)
) : ViewHolder(itemView) {
    private val iconView by bind<ImageView>(R.id.data_list_item_icon)
    private val titleView by bind<TextView>(R.id.data_list_item_title)

    private lateinit var item: Service

    init {
        itemView.apply {
            (this as FrameLayout).setSelectableForeground()
            isClickable = true
            setOnClickWithDelayListener { onClick.invoke(item) }
        }
    }

    fun bind(service: Service) {
        item = service

        service.run {
            iconView.setImageResource(iconRes)
            iconView.isVisible = true
            titleView.setText(titleRes)
        }
    }
}
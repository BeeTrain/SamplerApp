package ru.chernakov.sampler.main.services.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ru.chernakov.sampler.main.services.R

enum class Service(
    @DrawableRes val iconRes: Int,
    @StringRes val titleRes: Int
) {
    SWIPER(R.drawable.ic_sampler, R.string.title_item_swiper),
    LOGBOOK(R.drawable.ic_sampler, R.string.title_item_logbook)
}
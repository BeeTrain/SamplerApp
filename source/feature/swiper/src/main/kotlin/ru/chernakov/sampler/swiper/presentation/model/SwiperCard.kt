package ru.chernakov.sampler.swiper.presentation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class SwiperCard(
    @DrawableRes val imageRes: Int,
    @StringRes val nameRes: Int,
    @StringRes val loopUrl: Int
)
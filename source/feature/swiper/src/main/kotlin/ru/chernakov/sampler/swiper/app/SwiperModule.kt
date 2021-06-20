package ru.chernakov.sampler.swiper.app

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.chernakov.sampler.swiper.presentation.SwiperViewModel

val swiperModule = module {
    viewModel { SwiperViewModel() }
}
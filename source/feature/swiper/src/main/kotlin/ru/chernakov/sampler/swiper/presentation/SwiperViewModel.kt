package ru.chernakov.sampler.swiper.presentation

import androidx.lifecycle.MutableLiveData
import ru.chernakov.sampler.coreui.presentation.viewmodel.BaseViewModel
import ru.chernakov.sampler.swiper.R
import ru.chernakov.sampler.swiper.presentation.model.SwiperCard
import ru.chernakov.sampler.swiper.presentation.model.SwiperModel

class SwiperViewModel : BaseViewModel() {

    val cardsLiveData = MutableLiveData<SwiperModel>()

    private val data = listOf(
        SwiperCard(R.color.blue_200),
        SwiperCard(R.color.green_100),
        SwiperCard(R.color.amber_200),
        SwiperCard(R.color.indigo_300)
    )

    private var currentIndex = 0

    private val topCard
        get() = data[currentIndex % data.size]

    private val bottomCard
        get() = data[(currentIndex + 1) % data.size]

    init {
        updateCards()
    }

    fun swipe() {
        currentIndex += 1
        updateCards()
    }

    private fun updateCards() {
        cardsLiveData.value = SwiperModel(topCard, bottomCard)
    }
}
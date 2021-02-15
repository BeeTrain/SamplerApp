package ru.chernakov.sampler.swiper.presentation

import androidx.lifecycle.MutableLiveData
import ru.chernakov.sampler.coreui.presentation.viewmodel.BaseViewModel
import ru.chernakov.sampler.swiper.R
import ru.chernakov.sampler.swiper.presentation.model.SwiperCard
import ru.chernakov.sampler.swiper.presentation.model.SwiperModel

class SwiperViewModel : BaseViewModel() {

    val cardsLiveData = MutableLiveData<SwiperModel>()

    private val data = listOf(
        SwiperCard(R.drawable.img_geralt, R.string.name_geralt, R.string.url_geralt),
        SwiperCard(R.drawable.img_ciri, R.string.name_ciri, R.string.url_ciri),
        SwiperCard(R.drawable.img_yennefer, R.string.name_yennefer, R.string.url_yennefer),
        SwiperCard(R.drawable.img_triss, R.string.name_triss, R.string.url_triss),
        SwiperCard(R.drawable.img_vesemir, R.string.name_vesemir, R.string.url_vesemir),
        SwiperCard(R.drawable.img_eskel, R.string.name_eskel, R.string.url_eskel),
        SwiperCard(R.drawable.img_lambert, R.string.name_lambert, R.string.url_lambert)
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
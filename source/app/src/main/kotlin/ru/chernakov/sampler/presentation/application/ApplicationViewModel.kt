package ru.chernakov.sampler.presentation.application

import androidx.lifecycle.MutableLiveData
import ru.chernakov.sampler.coreui.presentation.viewmodel.BaseViewModel

class ApplicationViewModel : BaseViewModel() {
    val topInsetLiveData = MutableLiveData(DEFAULT_TOP_INSET_DP)
    val bottomInsetLiveData = MutableLiveData(DEFAULT_BOTTOM_INSET_DP)

    companion object {
        private const val DEFAULT_TOP_INSET_DP = 24f
        private const val DEFAULT_BOTTOM_INSET_DP = 48f
    }
}
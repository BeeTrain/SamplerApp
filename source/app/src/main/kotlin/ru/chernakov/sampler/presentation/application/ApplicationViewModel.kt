package ru.chernakov.sampler.presentation.application

import kotlinx.coroutines.flow.MutableStateFlow
import ru.chernakov.sampler.core.ui.presentation.viewmodel.BaseViewModel

class ApplicationViewModel : BaseViewModel() {

    val topInsetStateFlow = MutableStateFlow(DEFAULT_TOP_INSET_DP)

    val bottomInsetStateFlow = MutableStateFlow(DEFAULT_BOTTOM_INSET_DP)

    fun updateTopInset(topInsetDp: Float) {
        topInsetStateFlow.value = topInsetDp
    }

    fun updateBottomInset(bottomInsetDp: Float) {
        bottomInsetStateFlow.value = bottomInsetDp
    }

    companion object {
        private const val DEFAULT_TOP_INSET_DP = 24f
        private const val DEFAULT_BOTTOM_INSET_DP = 48f
    }
}
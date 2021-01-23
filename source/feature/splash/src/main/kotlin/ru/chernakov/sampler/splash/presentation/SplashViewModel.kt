package ru.chernakov.sampler.splash.presentation

import kotlinx.coroutines.delay
import ru.chernakov.sampler.coreui.presentation.viewmodel.BaseViewModel
import ru.chernakov.sampler.coreui.util.lifecycle.SingleLiveEvent

class SplashViewModel : BaseViewModel() {
    val redirectEvent = SingleLiveEvent<Boolean>()

    fun onSplashShowing() {
        launchLoadingErrorJob {
            delay(3000)
            redirectEvent.postValue(true)
        }
    }
}
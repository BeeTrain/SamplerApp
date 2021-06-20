package ru.chernakov.sampler.splash.presentation

import kotlinx.coroutines.delay
import ru.chernakov.sampler.core.ui.presentation.viewmodel.BaseViewModel
import ru.chernakov.sampler.core.ui.util.lifecycle.SingleLiveEvent

class SplashViewModel : BaseViewModel() {
    val redirectEvent = SingleLiveEvent<Boolean>()

    fun onSplashShowing() {
        launchLoadingErrorJob {
            delay(3000)
            redirectEvent.postValue(true)
        }
    }
}
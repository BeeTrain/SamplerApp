package ru.chernakov.sampler.splash.presentation

import kotlinx.coroutines.delay
import ru.chernakov.sampler.core.ui.lifecycle.SingleSharedFlow
import ru.chernakov.sampler.core.ui.presentation.viewmodel.BaseViewModel

class SplashViewModel : BaseViewModel() {
    val redirectEvent = SingleSharedFlow<Boolean>()

    fun onSplashShowing() {
        launchLoadingErrorJob {
            delay(3000)
            redirectEvent.tryEmit(true)
        }
    }
}
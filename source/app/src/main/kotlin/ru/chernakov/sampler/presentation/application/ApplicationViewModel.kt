package ru.chernakov.sampler.presentation.application

import androidx.lifecycle.MutableLiveData
import ru.chernakov.sampler.coreui.presentation.viewmodel.BaseViewModel
import ru.chernakov.sampler.settings.domain.interactor.SettingsInteractor

class ApplicationViewModel(settingsInteractor: SettingsInteractor) : BaseViewModel() {
    val topInsetLiveData = MutableLiveData(DEFAULT_TOP_INSET_DP)
    val bottomInsetLiveData = MutableLiveData(DEFAULT_BOTTOM_INSET_DP)

    val appThemeLiveData = settingsInteractor.getAppThemeObserver()

    companion object {
        private const val DEFAULT_TOP_INSET_DP = 24f
        private const val DEFAULT_BOTTOM_INSET_DP = 48f
    }
}
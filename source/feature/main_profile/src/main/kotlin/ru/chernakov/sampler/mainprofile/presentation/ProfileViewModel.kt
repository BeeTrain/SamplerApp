package ru.chernakov.sampler.mainprofile.presentation

import ru.chernakov.sampler.coreui.presentation.viewmodel.BaseViewModel
import ru.chernakov.sampler.mainprofile.domain.interactor.SettingsInteractor

class ProfileViewModel(private val settingsInteractor: SettingsInteractor) : BaseViewModel() {

    val appThemeLiveData = settingsInteractor.getAppThemeObserver()

    fun switchAppTheme(isEnabled: Boolean) {
        settingsInteractor.setNightModeEnabled(isEnabled)
    }
}
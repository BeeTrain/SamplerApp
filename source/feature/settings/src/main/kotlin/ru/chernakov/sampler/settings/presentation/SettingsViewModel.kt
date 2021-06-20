package ru.chernakov.sampler.settings.presentation

import ru.chernakov.sampler.core.ui.presentation.viewmodel.BaseViewModel
import ru.chernakov.sampler.settings.domain.interactor.SettingsInteractor

class SettingsViewModel(private val settingsInteractor: SettingsInteractor) : BaseViewModel() {

    val appThemeLiveData = settingsInteractor.getAppThemeObserver()

    fun switchAppTheme(isEnabled: Boolean) {
        settingsInteractor.setNightModeEnabled(isEnabled)
    }
}
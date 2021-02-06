package ru.chernakov.sampler.mainprofile.domain.interactor

import ru.chernakov.sampler.mainprofile.domain.model.settings.AppTheme
import ru.chernakov.sampler.mainprofile.domain.model.settings.SettingsDataSource

class SettingsInteractor(private val settingsDataSource: SettingsDataSource) {

    fun getAppThemeObserver() = settingsDataSource.getAppThemeLiveData()

    fun setNightModeEnabled(isEnabled: Boolean) {
        val theme = if (isEnabled) AppTheme.DARK else AppTheme.LIGHT
        settingsDataSource.setAppTheme(theme)
    }
}
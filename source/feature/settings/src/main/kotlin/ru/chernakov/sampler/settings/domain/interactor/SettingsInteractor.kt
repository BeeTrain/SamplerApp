package ru.chernakov.sampler.settings.domain.interactor

import ru.chernakov.sampler.settings.domain.model.AppTheme
import ru.chernakov.sampler.settings.domain.repository.SettingsRepository

class SettingsInteractor(private val settingsRepository: SettingsRepository) {

    fun getAppThemeObserver() = settingsRepository.getAppThemeLiveData()

    fun setNightModeEnabled(isEnabled: Boolean) {
        val theme = if (isEnabled) AppTheme.DARK else AppTheme.LIGHT
        settingsRepository.setAppTheme(theme)
    }
}
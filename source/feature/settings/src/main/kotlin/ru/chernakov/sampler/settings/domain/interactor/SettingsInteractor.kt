package ru.chernakov.sampler.settings.domain.interactor

import ru.chernakov.sampler.apptheme.api.model.AppTheme
import ru.chernakov.sampler.settings.domain.repository.SettingsRepository

class SettingsInteractor(private val settingsRepository: SettingsRepository) {

    fun getAppThemeObserver() = settingsRepository.getAppThemeLiveData()

    fun setNightModeEnabled(isEnabled: Boolean) {
        val theme = AppTheme.DARK.takeIf { isEnabled } ?: AppTheme.LIGHT
        settingsRepository.setAppTheme(theme)
    }
}
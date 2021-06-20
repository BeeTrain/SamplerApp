package ru.chernakov.sampler.settings.domain.interactor

import ru.chernakov.sampler.settings.domain.repository.SettingsRepository
import ru.chernakov.sampler.theme.api.model.AppTheme

class SettingsInteractor(private val settingsRepository: SettingsRepository) {

    fun getAppThemeObserver() = settingsRepository.getAppThemeLiveData()

    fun setNightModeEnabled(isEnabled: Boolean) {
        val theme = AppTheme.DARK.takeIf { isEnabled } ?: AppTheme.LIGHT
        settingsRepository.setAppTheme(theme)
    }
}
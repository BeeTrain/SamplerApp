package ru.chernakov.sampler.settings.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.chernakov.sampler.settings.domain.repository.SettingsRepository
import ru.chernakov.sampler.theme.api.ThemeService
import ru.chernakov.sampler.theme.api.model.AppTheme

class AppSettingsRepository(
    private val appThemeApplier: ThemeService
) : SettingsRepository {

    private val appThemeState = MutableStateFlow(getAppTheme())

    override fun getAppTheme(): AppTheme {
        return appThemeApplier.getCurrentAppTheme()
    }

    override fun setAppTheme(theme: AppTheme) {
        appThemeApplier.applyAppTheme(theme)
        appThemeState.value = theme
    }

    override fun getAppThemeState() = appThemeState.asStateFlow()
}
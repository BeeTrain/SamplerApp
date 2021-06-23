package ru.chernakov.sampler.settings.domain.repository

import kotlinx.coroutines.flow.StateFlow
import ru.chernakov.sampler.theme.api.model.AppTheme

interface SettingsRepository {

    fun getAppTheme(): AppTheme

    fun setAppTheme(theme: AppTheme)

    fun getAppThemeState(): StateFlow<AppTheme>
}
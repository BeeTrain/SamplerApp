package ru.chernakov.sampler.theme

import androidx.appcompat.app.AppCompatDelegate
import ru.chernakov.sampler.theme.api.ThemeService
import ru.chernakov.sampler.theme.api.ThemeStorage
import ru.chernakov.sampler.theme.api.model.AppTheme
import ru.chernakov.sampler.theme.data.mapper.AppThemeMapper

class AppThemeApplier(
    private val appThemeStorage: ThemeStorage,
    private val appThemeMapper: AppThemeMapper
) : ThemeService {

    init {
        val currentAppTheme = appThemeStorage.getAppTheme()
        applyNightModeByTheme(currentAppTheme)
    }

    override fun applyAppTheme(appTheme: AppTheme) {
        appThemeStorage.saveAppTheme(appTheme)
        applyNightModeByTheme(appTheme)
    }

    override fun getCurrentAppTheme(): AppTheme {
        return appThemeStorage.getAppTheme()
    }

    private fun applyNightModeByTheme(appTheme: AppTheme) {
        val appCompatTheme = appThemeMapper.mapToAppCompatTheme(appTheme)
        AppCompatDelegate.setDefaultNightMode(appCompatTheme)
    }
}
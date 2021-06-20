package ru.chernakov.sampler.theme.data.repository

import ru.chernakov.sampler.core.data.storage.SimpleDataStorage
import ru.chernakov.sampler.theme.api.ThemeStorage
import ru.chernakov.sampler.theme.api.model.AppTheme
import ru.chernakov.sampler.theme.data.mapper.AppThemeMapper

private const val KEY_APP_THEME = "APP_THEME"

class AppThemeStorage(
    private val simpleDataStorage: SimpleDataStorage,
    private val appThemeMapper: AppThemeMapper
) : ThemeStorage {

    override fun getAppTheme(): AppTheme {
        val preferencesAppThemeValue = simpleDataStorage.getNullableInt(KEY_APP_THEME)

        return appThemeMapper.mapToAppTheme(preferencesAppThemeValue)
    }

    override fun saveAppTheme(appTheme: AppTheme) {
        val appCompatTheme = appThemeMapper.mapToAppCompatTheme(appTheme)
        simpleDataStorage.putInt(KEY_APP_THEME, appCompatTheme)
    }
}
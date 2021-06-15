package ru.chernakov.sampler.apptheme.data.repository

import ru.chernakov.sampler.apptheme.api.ThemeStorage
import ru.chernakov.sampler.apptheme.api.model.AppTheme
import ru.chernakov.sampler.apptheme.data.mapper.AppThemeMapper
import ru.chernakov.sampler.coredata.storage.SimpleDataStorage

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
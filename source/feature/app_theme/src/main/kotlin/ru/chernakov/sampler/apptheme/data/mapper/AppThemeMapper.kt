package ru.chernakov.sampler.apptheme.data.mapper

import androidx.appcompat.app.AppCompatDelegate
import ru.chernakov.sampler.apptheme.api.model.AppTheme

class AppThemeMapper {

    fun mapToAppCompatTheme(appTheme: AppTheme): Int {
        return when (appTheme) {
            AppTheme.LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
            AppTheme.DARK -> AppCompatDelegate.MODE_NIGHT_YES
            AppTheme.UNDEFINED -> AppCompatDelegate.MODE_NIGHT_NO
        }
    }

    fun mapToAppTheme(internalValue: Int?): AppTheme {
        return when (internalValue) {
            AppCompatDelegate.MODE_NIGHT_NO -> AppTheme.LIGHT
            AppCompatDelegate.MODE_NIGHT_YES -> AppTheme.DARK
            else -> AppTheme.UNDEFINED
        }
    }
}
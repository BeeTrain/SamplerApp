package ru.chernakov.sampler.mainprofile.data.mapper

import androidx.appcompat.app.AppCompatDelegate
import ru.chernakov.sampler.mainprofile.domain.model.AppTheme

class AppThemeMapper {

    fun mapToInternal(appTheme: AppTheme): Int {
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
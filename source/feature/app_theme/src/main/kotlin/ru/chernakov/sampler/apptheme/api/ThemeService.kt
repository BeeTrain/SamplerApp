package ru.chernakov.sampler.apptheme.api

import ru.chernakov.sampler.apptheme.api.model.AppTheme

interface ThemeService {

    fun applyAppTheme(appTheme: AppTheme)

    fun getCurrentAppTheme(): AppTheme
}
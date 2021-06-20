package ru.chernakov.sampler.theme.api

import ru.chernakov.sampler.theme.api.model.AppTheme

interface ThemeService {

    fun applyAppTheme(appTheme: AppTheme)

    fun getCurrentAppTheme(): AppTheme
}
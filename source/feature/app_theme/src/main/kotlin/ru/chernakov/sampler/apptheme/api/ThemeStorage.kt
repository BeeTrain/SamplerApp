package ru.chernakov.sampler.apptheme.api

import ru.chernakov.sampler.apptheme.api.model.AppTheme

interface ThemeStorage {

    fun getAppTheme(): AppTheme

    fun saveAppTheme(appTheme: AppTheme)
}
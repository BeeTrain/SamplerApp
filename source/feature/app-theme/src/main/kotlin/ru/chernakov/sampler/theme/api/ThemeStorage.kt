package ru.chernakov.sampler.theme.api

import ru.chernakov.sampler.theme.api.model.AppTheme

interface ThemeStorage {

    fun getAppTheme(): AppTheme

    fun saveAppTheme(appTheme: AppTheme)
}
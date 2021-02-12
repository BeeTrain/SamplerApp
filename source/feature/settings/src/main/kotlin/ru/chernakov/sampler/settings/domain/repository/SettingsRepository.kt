package ru.chernakov.sampler.settings.domain.repository

import androidx.lifecycle.LiveData
import ru.chernakov.sampler.settings.domain.model.AppTheme

interface SettingsRepository {

    fun getAppTheme(): AppTheme
    fun setAppTheme(theme: AppTheme)
    fun getAppThemeLiveData(): LiveData<AppTheme>
}
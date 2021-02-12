package ru.chernakov.sampler.mainprofile.domain.repository

import androidx.lifecycle.LiveData
import ru.chernakov.sampler.mainprofile.domain.model.AppTheme

interface SettingsRepository {

    fun getAppTheme(): AppTheme
    fun setAppTheme(theme: AppTheme)
    fun getAppThemeLiveData(): LiveData<AppTheme>
}
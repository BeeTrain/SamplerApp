package ru.chernakov.sampler.mainprofile.domain.model.settings

import androidx.lifecycle.LiveData

interface SettingsDataSource {

    fun getAppTheme(): AppTheme
    fun setAppTheme(theme: AppTheme)
    fun getAppThemeLiveData(): LiveData<AppTheme>
}
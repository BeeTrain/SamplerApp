package ru.chernakov.sampler.settings.data.repository

import androidx.lifecycle.MutableLiveData
import ru.chernakov.sampler.apptheme.api.ThemeService
import ru.chernakov.sampler.apptheme.api.model.AppTheme
import ru.chernakov.sampler.coreui.extension.asLiveData
import ru.chernakov.sampler.settings.domain.repository.SettingsRepository

class AppSettingsRepository(
    private val appThemeApplier: ThemeService
) : SettingsRepository {

    private val appThemeLiveData = MutableLiveData<AppTheme>()

    init {
        appThemeLiveData.value = getAppTheme()
    }

    override fun getAppTheme(): AppTheme {
        return appThemeApplier.getCurrentAppTheme()
    }

    override fun setAppTheme(theme: AppTheme) {
        appThemeApplier.applyAppTheme(theme)
        appThemeLiveData.value = theme
    }

    override fun getAppThemeLiveData() = appThemeLiveData.asLiveData()

}
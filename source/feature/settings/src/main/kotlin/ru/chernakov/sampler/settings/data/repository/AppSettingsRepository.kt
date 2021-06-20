package ru.chernakov.sampler.settings.data.repository

import androidx.lifecycle.MutableLiveData
import ru.chernakov.sampler.core.ui.extension.asLiveData
import ru.chernakov.sampler.settings.domain.repository.SettingsRepository
import ru.chernakov.sampler.theme.api.ThemeService
import ru.chernakov.sampler.theme.api.model.AppTheme

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
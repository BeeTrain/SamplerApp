package ru.chernakov.sampler.mainprofile.data.repository

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import ru.chernakov.sampler.coredata.prefs.SharedPreferencesApi
import ru.chernakov.sampler.coreui.extension.asLiveData
import ru.chernakov.sampler.mainprofile.domain.model.settings.AppTheme
import ru.chernakov.sampler.mainprofile.domain.model.settings.SettingsDataSource

class SettingsRepository(
    private val preferences: SharedPreferencesApi,
    private val appThemeMapper: AppThemeMapper
) : SettingsDataSource {

    private val preferenceChangedListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        when (key) {
            KEY_APP_THEME -> appThemeLiveData.value = getAppTheme()
        }
    }

    private val appThemeLiveData = MutableLiveData<AppTheme>()

    init {
        preferences.registerChangeListener(preferenceChangedListener)

        appThemeLiveData.value = getAppTheme()
    }

    override fun getAppTheme(): AppTheme {
        return appThemeMapper.mapToAppTheme(preferences.getNullableInt(KEY_APP_THEME))
    }

    override fun setAppTheme(theme: AppTheme) {
        preferences.putInt(KEY_APP_THEME, appThemeMapper.mapToInternal(theme))
    }

    override fun getAppThemeLiveData() = appThemeLiveData.asLiveData()

    companion object {
        private const val KEY_APP_THEME = "APP_THEME"
    }
}
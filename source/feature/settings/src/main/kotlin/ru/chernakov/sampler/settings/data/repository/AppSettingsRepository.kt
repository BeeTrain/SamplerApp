package ru.chernakov.sampler.settings.data.repository

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import ru.chernakov.sampler.coredata.prefs.SharedPreferencesApi
import ru.chernakov.sampler.coreui.extension.asLiveData
import ru.chernakov.sampler.settings.data.mapper.AppThemeMapper
import ru.chernakov.sampler.settings.domain.model.AppTheme
import ru.chernakov.sampler.settings.domain.repository.SettingsRepository

class AppSettingsRepository(
    private val preferences: SharedPreferencesApi,
    private val appThemeMapper: AppThemeMapper
) : SettingsRepository {

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
package ru.chernakov.sampler.theme.app.di

import org.koin.dsl.module
import ru.chernakov.sampler.theme.AppThemeApplier
import ru.chernakov.sampler.theme.api.ThemeService
import ru.chernakov.sampler.theme.api.ThemeStorage
import ru.chernakov.sampler.theme.data.mapper.AppThemeMapper
import ru.chernakov.sampler.theme.data.repository.AppThemeStorage

val appThemeModule = module {

    single<ThemeService>(createdAtStart = true) {
        AppThemeApplier(
            appThemeStorage = get(),
            appThemeMapper = get()
        )
    }

    single<ThemeStorage> {
        AppThemeStorage(
            simpleDataStorage = get(),
            appThemeMapper = get()
        )
    }

    factory { AppThemeMapper() }
}
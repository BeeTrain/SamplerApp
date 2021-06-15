package ru.chernakov.sampler.apptheme.app.di

import org.koin.dsl.module
import ru.chernakov.sampler.apptheme.AppThemeApplier
import ru.chernakov.sampler.apptheme.api.ThemeService
import ru.chernakov.sampler.apptheme.api.ThemeStorage
import ru.chernakov.sampler.apptheme.data.mapper.AppThemeMapper
import ru.chernakov.sampler.apptheme.data.repository.AppThemeStorage

val appThemeModule = module {
    single<ThemeService>(createdAtStart = true) { AppThemeApplier(get(), get()) }
    single<ThemeStorage> { AppThemeStorage(get(), get()) }
    factory { AppThemeMapper() }
}
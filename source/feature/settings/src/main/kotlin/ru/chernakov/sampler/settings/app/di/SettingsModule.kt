package ru.chernakov.sampler.settings.app.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.chernakov.sampler.settings.data.repository.AppSettingsRepository
import ru.chernakov.sampler.settings.domain.interactor.SettingsInteractor
import ru.chernakov.sampler.settings.domain.repository.SettingsRepository
import ru.chernakov.sampler.settings.presentation.SettingsViewModel

val settingsModule = module {
    single<SettingsRepository> { AppSettingsRepository(get()) }

    factory { SettingsInteractor(get()) }

    viewModel { SettingsViewModel(get()) }
}
package ru.chernakov.sampler.mainprofile.app.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.chernakov.sampler.mainprofile.data.repository.AppThemeMapper
import ru.chernakov.sampler.mainprofile.data.repository.SettingsRepository
import ru.chernakov.sampler.mainprofile.domain.interactor.SettingsInteractor
import ru.chernakov.sampler.mainprofile.domain.model.settings.SettingsDataSource
import ru.chernakov.sampler.mainprofile.presentation.ProfileViewModel

val profileModule = module {
    single { AppThemeMapper() }
    single { SettingsRepository(get(), get()) }

    single<SettingsDataSource> { get<SettingsRepository>() }

    factory { SettingsInteractor(get()) }

    viewModel { ProfileViewModel(get()) }
}
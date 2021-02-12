package ru.chernakov.sampler.mainprofile.app.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.chernakov.sampler.mainprofile.data.mapper.AppThemeMapper
import ru.chernakov.sampler.mainprofile.data.repository.AppSettingsRepository
import ru.chernakov.sampler.mainprofile.domain.interactor.SettingsInteractor
import ru.chernakov.sampler.mainprofile.domain.repository.SettingsRepository
import ru.chernakov.sampler.mainprofile.presentation.ProfileViewModel

val profileModule = module {
    single { AppThemeMapper() }
    single { AppSettingsRepository(get(), get()) }

    single<SettingsRepository> { get<AppSettingsRepository>() }

    factory { SettingsInteractor(get()) }

    viewModel { ProfileViewModel(get()) }
}
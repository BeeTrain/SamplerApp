package ru.chernakov.sampler.main.profile.app.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.chernakov.sampler.main.profile.presentation.ProfileViewModel

val profileModule = module {
    viewModel { ProfileViewModel() }
}
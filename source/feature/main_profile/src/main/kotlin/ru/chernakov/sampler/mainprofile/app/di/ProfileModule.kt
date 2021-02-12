package ru.chernakov.sampler.mainprofile.app.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.chernakov.sampler.mainprofile.presentation.ProfileViewModel

val profileModule = module {
    viewModel { ProfileViewModel() }
}
package ru.chernakov.sampler.splash.api.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.chernakov.sampler.splash.presentation.SplashViewModel

val splashModule = module {
    viewModel { SplashViewModel() }
}
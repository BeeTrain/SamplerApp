package ru.chernakov.sampler.app.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.chernakov.sampler.presentation.application.ApplicationViewModel

val appModule = module {
    viewModel { ApplicationViewModel(get()) }
}
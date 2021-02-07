package ru.chernakov.sampler.main.app.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.chernakov.sampler.main.presentation.MainViewModel

val mainModule = module {
    viewModel { MainViewModel() }
}
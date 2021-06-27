package ru.chernakov.sampler.main.services.app.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.chernakov.sampler.main.services.presentation.ServicesViewModel

val servicesModule = module {
    viewModel {
        ServicesViewModel(
            navigator = get()
        )
    }
}
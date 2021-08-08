package ru.chernakov.sampler.logbook.app.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.chernakov.sampler.logbook.presentation.LogbookViewModel

val logbookModule = module {

    viewModel { LogbookViewModel() }
}
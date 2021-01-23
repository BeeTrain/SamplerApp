package ru.chernakov.sampler.app.di

import org.koin.dsl.module
import ru.chernakov.sampler.main.api.di.mainModule
import ru.chernakov.sampler.splash.api.di.splashModule

object ApplicationModule {
    private val applicationModule = module {

    }

    val modules = listOf(
        applicationModule,
        navigationModule,
        appModule,
        splashModule,
        mainModule,
    )
}
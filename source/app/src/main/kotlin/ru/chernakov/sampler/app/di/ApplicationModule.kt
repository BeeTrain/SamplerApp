package ru.chernakov.sampler.app.di

import org.koin.dsl.module
import ru.chernakov.sampler.coredata.api.di.dataModule
import ru.chernakov.sampler.main.api.di.mainModule
import ru.chernakov.sampler.mainprofile.api.di.profileModule
import ru.chernakov.sampler.splash.api.di.splashModule

object ApplicationModule {
    private val applicationModule = module {
    }

    val modules = listOf(
        applicationModule,
        dataModule,
        navigationModule,
        appModule,
        splashModule,
        mainModule,
        profileModule,
    )
}
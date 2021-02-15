package ru.chernakov.sampler.app.di

import org.koin.dsl.module
import ru.chernakov.sampler.coredata.app.di.dataModule
import ru.chernakov.sampler.main.app.di.mainModule
import ru.chernakov.sampler.mainprofile.app.di.profileModule
import ru.chernakov.sampler.navigation.app.di.navigationModule
import ru.chernakov.sampler.settings.app.di.settingsModule
import ru.chernakov.sampler.splash.app.di.splashModule
import ru.chernakov.sampler.swiper.app.swiperModule

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
        settingsModule,
        swiperModule
    )
}
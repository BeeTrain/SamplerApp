package ru.chernakov.sampler.app.di

import org.koin.dsl.module
import ru.chernakov.sampler.core.data.app.di.dataModule
import ru.chernakov.sampler.main.app.di.mainModule
import ru.chernakov.sampler.main.profile.app.di.profileModule
import ru.chernakov.sampler.navigation.app.di.navigationModule
import ru.chernakov.sampler.settings.app.di.settingsModule
import ru.chernakov.sampler.splash.app.di.splashModule
import ru.chernakov.sampler.swiper.app.swiperModule
import ru.chernakov.sampler.theme.app.di.appThemeModule

object ApplicationModule {
    private val applicationModule = module {
    }

    val modules = listOf(
        applicationModule,
        appThemeModule,
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
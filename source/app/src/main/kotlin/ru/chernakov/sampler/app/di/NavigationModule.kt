package ru.chernakov.sampler.app.di

import org.koin.dsl.module
import ru.chernakov.sampler.app.navigation.AppNavigator
import ru.chernakov.sampler.app.navigation.ApplicationNavigator
import ru.chernakov.sampler.main.api.navigation.MainNavigator
import ru.chernakov.sampler.splash.api.navigation.SplashNavigator

val navigationModule = module {
    single { ApplicationNavigator() }

    single<AppNavigator> { get<ApplicationNavigator>() }
    single<SplashNavigator> { get<ApplicationNavigator>() }
    single<MainNavigator> { get<ApplicationNavigator>() }
}
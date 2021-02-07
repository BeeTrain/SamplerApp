package ru.chernakov.sampler.navigation.api.di

import org.koin.dsl.module
import ru.chernakov.sampler.main.api.navigation.MainNavigator
import ru.chernakov.sampler.navigation.navigator.AppNavigator
import ru.chernakov.sampler.navigation.navigator.ApplicationNavigator
import ru.chernakov.sampler.splash.api.navigation.SplashNavigator

val navigationModule = module {
    single { ApplicationNavigator() }

    single<AppNavigator> { get<ApplicationNavigator>() }
    single<SplashNavigator> { get<ApplicationNavigator>() }
    single<MainNavigator> { get<ApplicationNavigator>() }
}
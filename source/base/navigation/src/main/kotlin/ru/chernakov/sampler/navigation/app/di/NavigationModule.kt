package ru.chernakov.sampler.navigation.app.di

import org.koin.dsl.module
import ru.chernakov.sampler.main.app.navigation.MainNavigator
import ru.chernakov.sampler.main.profile.app.navigation.ProfileNavigator
import ru.chernakov.sampler.main.services.app.navigation.ServicesNavigator
import ru.chernakov.sampler.navigation.navigator.AppNavigator
import ru.chernakov.sampler.navigation.navigator.ApplicationNavigator
import ru.chernakov.sampler.settings.app.navigation.SettingsNavigator
import ru.chernakov.sampler.splash.app.navigation.SplashNavigator

val navigationModule = module {

    single { ApplicationNavigator() }

    single<AppNavigator> { get<ApplicationNavigator>() }

    single<SplashNavigator> { get<ApplicationNavigator>() }

    single<MainNavigator> { get<ApplicationNavigator>() }

    single<ProfileNavigator> { get<ApplicationNavigator>() }

    single<ServicesNavigator> { get<ApplicationNavigator>() }

    single<SettingsNavigator> { get<ApplicationNavigator>() }
}
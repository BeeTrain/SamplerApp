package ru.chernakov.sampler.navigation.navigator

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigator
import ru.chernakov.sampler.core.ui.extension.setFadeAnim
import ru.chernakov.sampler.core.ui.extension.setHorizontalFullInAnim
import ru.chernakov.sampler.core.ui.extension.setSlideInFromBottomFullAnim
import ru.chernakov.sampler.main.app.navigation.MainNavigator
import ru.chernakov.sampler.main.presentation.MainFragmentDirections
import ru.chernakov.sampler.main.profile.app.navigation.ProfileNavigator
import ru.chernakov.sampler.main.services.app.navigation.ServicesNavigator
import ru.chernakov.sampler.settings.app.navigation.SettingsNavigator
import ru.chernakov.sampler.splash.app.navigation.SplashNavigator
import ru.chernakov.sampler.splash.presentation.SplashFragmentDirections

class ApplicationNavigator :
    AppNavigator,
    SplashNavigator,
    MainNavigator,
    ProfileNavigator,
    SettingsNavigator,
    ServicesNavigator {

    private var appNavController: NavController? = null

    override fun bindAppController(navController: NavController) {
        appNavController = navController
    }

    override fun unbindAppController() {
        appNavController = null
    }

    override fun fromSplashToMain() {
        appNavController?.navigate(
            action = SplashFragmentDirections.actionOpenMain(),
            navOptions = NavOptions.Builder().setHorizontalFullInAnim().build()
        )
    }

    override fun fromProfileToSettings() {
        appNavController?.navigate(
            action = MainFragmentDirections.actionFromMainToSettings(),
            navOptions = NavOptions.Builder().setSlideInFromBottomFullAnim().build()
        )
    }

    override fun fromSettingsToMain() {
        appNavController?.popBackStack()
    }

    override fun fromServicesToSwiper() {
        appNavController?.navigate(
            action = MainFragmentDirections.actionFromMainToSwiper(),
            navOptions = NavOptions.Builder().setHorizontalFullInAnim().build()
        )
    }

    private fun NavController.navigate(
        action: NavDirections,
        args: Bundle? = null,
        navExtras: FragmentNavigator.Extras? = null,
        navOptions: NavOptions? = NavOptions.Builder().setFadeAnim().build()
    ) {
        navigate(action.actionId, args, navOptions, navExtras)
    }
}
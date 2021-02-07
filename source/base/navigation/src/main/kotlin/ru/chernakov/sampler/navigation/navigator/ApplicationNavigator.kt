package ru.chernakov.sampler.navigation.navigator

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigator
import ru.chernakov.sampler.coreui.extension.setFadeAnim
import ru.chernakov.sampler.coreui.extension.setHorizontalFullInAnim
import ru.chernakov.sampler.main.app.navigation.MainNavigator
import ru.chernakov.sampler.splash.app.navigation.SplashNavigator
import ru.chernakov.sampler.splash.presentation.SplashFragmentDirections

class ApplicationNavigator : AppNavigator, SplashNavigator, MainNavigator {

    private var appNavController: NavController? = null

    override fun bindAppController(navController: NavController) {
        appNavController = navController
    }

    override fun unbindAppController() {
        appNavController = null
    }

    override fun fromSplashToMain() {
        navigate(
            action = SplashFragmentDirections.actionOpenMain(),
            navOptions = NavOptions.Builder().setHorizontalFullInAnim().build()
        )
    }

    private fun navigate(
        action: NavDirections,
        args: Bundle? = null,
        navExtras: FragmentNavigator.Extras? = null,
        navOptions: NavOptions? = NavOptions.Builder().setFadeAnim().build()
    ) {
        appNavController?.navigate(action.actionId, args, navOptions, navExtras)
    }
}
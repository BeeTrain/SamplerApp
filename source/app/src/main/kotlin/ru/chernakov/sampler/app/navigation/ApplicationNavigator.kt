package ru.chernakov.sampler.app.navigation

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import ru.chernakov.sampler.main.api.navigation.MainNavigator
import ru.chernakov.sampler.splash.api.navigation.SplashNavigator
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
        navigate(SplashFragmentDirections.actionOpenMain())
    }

    private fun navigate(
        action: NavDirections,
        args: Bundle? = null,
        navExtras: FragmentNavigator.Extras? = null
    ) {
        appNavController?.navigate(action.actionId, args, null, navExtras)
    }
}
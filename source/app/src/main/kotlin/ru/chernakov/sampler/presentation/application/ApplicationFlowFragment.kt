package ru.chernakov.sampler.presentation.application

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment

class ApplicationFlowFragment : NavHostFragment() {
    private var destChangeListener = createDestinationChangeListener()

    override fun onStart() {
        super.onStart()
        navController.addOnDestinationChangedListener(destChangeListener)
    }

    override fun onStop() {
        super.onStop()
        navController.removeOnDestinationChangedListener(destChangeListener)
    }

    // This needed to remove memory leak causing because of using NavHostFragments inside another NavHostFragment
    private fun createDestinationChangeListener(): NavController.OnDestinationChangedListener {
        return NavController.OnDestinationChangedListener { _, _, _ ->
            view?.let { Navigation.setViewNavController(it, navController) }
        }
    }
}

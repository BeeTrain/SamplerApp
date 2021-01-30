package ru.chernakov.sampler.coreui.presentation.fragment

import androidx.annotation.NavigationRes


abstract class BaseBottomNavFlowFragment(@NavigationRes private val navGraphId: Int) : BaseFlowFragment() {

    override fun onBackPressed() {
        if (navController.navigateUp()) {
            return
        }

        (parentFragment as BaseFlowFragment).onBackPressed()
    }

    override fun getGraphResId() = navGraphId
}

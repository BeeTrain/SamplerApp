package ru.chernakov.sampler.splash.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.chernakov.sampler.core.ui.extension.observeOnCreated
import ru.chernakov.sampler.core.ui.presentation.fragment.BaseFragment
import ru.chernakov.sampler.splash.R
import ru.chernakov.sampler.splash.app.navigation.SplashNavigator

class SplashFragment : BaseFragment(R.layout.fragment_splash) {
    private val navigator: SplashNavigator by inject()
    override val viewModel: SplashViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.redirectEvent.observeOnCreated(lifecycleScope) { navigator.fromSplashToMain() }
        viewModel.onSplashShowing()
    }
}
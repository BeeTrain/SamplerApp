package ru.chernakov.sampler.splash.presentation

import android.os.Bundle
import android.view.View
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.chernakov.sampler.coreui.presentation.fragment.BaseFragment
import ru.chernakov.sampler.splash.R
import ru.chernakov.sampler.splash.app.navigation.SplashNavigator

class SplashFragment : BaseFragment(R.layout.fragment_splash) {
    private val navigator: SplashNavigator by inject()
    override val viewModel: SplashViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {
            redirectEvent.observe(viewLifecycleOwner) {
                navigator.fromSplashToMain()
            }
        }

        viewModel.onSplashShowing()
    }
}
package ru.chernakov.sampler.main.services.presentation

import android.os.Bundle
import android.view.View
import org.koin.android.ext.android.inject
import ru.chernakov.sampler.core.ui.extension.findView
import ru.chernakov.sampler.core.ui.extension.setOnClickWithDelayListener
import ru.chernakov.sampler.core.ui.presentation.fragment.BaseFragment
import ru.chernakov.sampler.core.ui.presentation.viewmodel.BaseViewModel
import ru.chernakov.sampler.main.services.R
import ru.chernakov.sampler.main.services.app.navigation.ServicesNavigator
import ru.chernakov.sampler.widget.text.TextView

class ServicesFragment : BaseFragment(R.layout.fragment_services) {
    private val navigator: ServicesNavigator by inject()
    override val viewModel = BaseViewModel()

    private val servicesSwiperButton by findView<TextView>(R.id.services_swiper_button)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        servicesSwiperButton.setOnClickWithDelayListener { navigator.fromServicesToSwiper() }
    }
}
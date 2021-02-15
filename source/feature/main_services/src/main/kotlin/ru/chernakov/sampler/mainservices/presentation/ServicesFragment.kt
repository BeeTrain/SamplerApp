package ru.chernakov.sampler.mainservices.presentation

import android.os.Bundle
import android.view.View
import org.koin.android.ext.android.inject
import ru.chernakov.sampler.coreui.extension.findView
import ru.chernakov.sampler.coreui.extension.setOnClickWithDelayListener
import ru.chernakov.sampler.coreui.presentation.fragment.BaseFragment
import ru.chernakov.sampler.coreui.presentation.viewmodel.BaseViewModel
import ru.chernakov.sampler.mainservices.R
import ru.chernakov.sampler.mainservices.app.navigation.ServicesNavigator
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
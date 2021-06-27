package ru.chernakov.sampler.main.services.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import ru.chernakov.sampler.core.ui.presentation.viewmodel.BaseViewModel
import ru.chernakov.sampler.main.services.app.navigation.ServicesNavigator
import ru.chernakov.sampler.main.services.presentation.model.Service

class ServicesViewModel(
    private val navigator: ServicesNavigator
) : BaseViewModel() {

    val servicesState = MutableStateFlow(Service.values().toList())

    fun onServiceSelected(service: Service) {
        when (service) {
            Service.SWIPER -> navigator.fromServicesToSwiper()
        }
    }
}
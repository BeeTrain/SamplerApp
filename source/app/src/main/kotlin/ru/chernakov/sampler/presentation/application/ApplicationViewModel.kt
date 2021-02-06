package ru.chernakov.sampler.presentation.application

import ru.chernakov.sampler.coreui.presentation.viewmodel.BaseViewModel
import ru.chernakov.sampler.mainprofile.domain.interactor.SettingsInteractor

class ApplicationViewModel(settingsInteractor: SettingsInteractor) : BaseViewModel() {

    val appThemeLiveData = settingsInteractor.getAppThemeObserver()

}
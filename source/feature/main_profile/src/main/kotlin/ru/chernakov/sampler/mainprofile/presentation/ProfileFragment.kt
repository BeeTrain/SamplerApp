package ru.chernakov.sampler.mainprofile.presentation

import ru.chernakov.sampler.coreui.presentation.fragment.BaseFragment
import ru.chernakov.sampler.coreui.presentation.viewmodel.BaseViewModel
import ru.chernakov.sampler.mainprofile.R

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {
    override val viewModel = BaseViewModel()
}
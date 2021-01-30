package ru.chernakov.sampler.mainfeed.presentation

import ru.chernakov.sampler.coreui.presentation.fragment.BaseFragment
import ru.chernakov.sampler.coreui.presentation.viewmodel.BaseViewModel
import ru.chernakov.sampler.mainfeed.R

class FeedFragment : BaseFragment(R.layout.fragment_feed) {
    override val viewModel = BaseViewModel()
}
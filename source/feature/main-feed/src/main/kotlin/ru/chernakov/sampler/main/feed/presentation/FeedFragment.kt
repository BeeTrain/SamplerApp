package ru.chernakov.sampler.main.feed.presentation

import ru.chernakov.sampler.core.ui.presentation.fragment.BaseFragment
import ru.chernakov.sampler.core.ui.presentation.viewmodel.BaseViewModel
import ru.chernakov.sampler.mainfeed.R

class FeedFragment : BaseFragment(R.layout.fragment_feed) {
    override val viewModel = BaseViewModel()
}
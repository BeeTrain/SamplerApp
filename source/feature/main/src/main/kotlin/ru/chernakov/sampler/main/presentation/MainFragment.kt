package ru.chernakov.sampler.main.presentation

import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.chernakov.sampler.coreui.presentation.fragment.BaseFragment
import ru.chernakov.sampler.main.R

class MainFragment : BaseFragment(R.layout.fragment_main) {
    override val viewModel: MainViewModel by viewModel()
}
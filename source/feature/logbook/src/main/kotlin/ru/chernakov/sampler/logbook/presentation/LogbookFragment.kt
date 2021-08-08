package ru.chernakov.sampler.logbook.presentation

import android.os.Bundle
import android.view.View
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.chernakov.sampler.core.ui.extension.findView
import ru.chernakov.sampler.core.ui.presentation.fragment.BaseFragment
import ru.chernakov.sampler.logbook.R
import ru.chernakov.sampler.widget.appbar.Toolbar

class LogbookFragment : BaseFragment(R.layout.fragment_logbook) {

    override val viewModel: LogbookViewModel by viewModel()

    private val logbookToolbar by findView<Toolbar>(R.id.logbook_toolbar)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logbookToolbar.setNavigationOnClickListener { activity?.onBackPressed() }
    }
}
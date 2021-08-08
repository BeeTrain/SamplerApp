package ru.chernakov.sampler.main.services.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.chernakov.sampler.core.ui.extension.findView
import ru.chernakov.sampler.core.ui.extension.observeOnCreated
import ru.chernakov.sampler.core.ui.presentation.fragment.BaseFragment
import ru.chernakov.sampler.main.services.R
import ru.chernakov.sampler.main.services.data.model.Service
import ru.chernakov.sampler.main.services.presentation.adapter.ServicesAdapter
import ru.chernakov.sampler.widget.list.RecyclerView

class ServicesFragment : BaseFragment(R.layout.fragment_services) {
    override val viewModel: ServicesViewModel by viewModel()

    private val servicesRecyclerView by findView<RecyclerView>(R.id.services_recycler_view)

    private val adapter by lazy { ServicesAdapter(onListItemClick) }

    private val onListItemClick: ((Service) -> Unit) = { viewModel.onServiceSelected(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.servicesState.observeOnCreated(lifecycleScope) {
            populateList(it)
        }

        servicesRecyclerView.adapter = adapter
    }

    private fun populateList(services: List<Service>) {
        adapter.populate(services)
    }
}
package ru.chernakov.sampler.main.services.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.chernakov.sampler.main.services.R
import ru.chernakov.sampler.main.services.presentation.model.Service
import ru.chernakov.sampler.widget.list.RecyclerAdapter
import ru.chernakov.sampler.widget.list.ViewHolder

class ServicesAdapter(
    private val onItemClick: ((Service) -> Unit)
) : RecyclerAdapter<ServicesViewHolder>() {
    private val items = mutableListOf<Service>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.data_list_item, parent, false)

        return ServicesViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as ServicesViewHolder).bind(items[position])
    }

    override fun getItemCount() = items.size

    fun populate(newItems: List<Service>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}
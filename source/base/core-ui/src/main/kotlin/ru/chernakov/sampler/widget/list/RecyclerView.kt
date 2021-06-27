package ru.chernakov.sampler.widget.list

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr)

open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

abstract class RecyclerAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder

    abstract fun onBindViewHolder(holder: ViewHolder, position: Int)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        onBindViewHolder(holder as ViewHolder, position)
    }
}
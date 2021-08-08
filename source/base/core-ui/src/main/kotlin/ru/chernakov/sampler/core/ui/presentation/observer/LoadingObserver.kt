package ru.chernakov.sampler.core.ui.presentation.observer

import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer

class LoadingObserver(private val loadingView: View?) : Observer<Boolean?> {

    override fun onChanged(loading: Boolean?) {
        loadingView?.isVisible = loading ?: false
    }
}
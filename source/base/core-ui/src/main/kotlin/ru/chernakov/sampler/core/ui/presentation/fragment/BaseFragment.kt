package ru.chernakov.sampler.core.ui.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.StateFlow
import ru.chernakov.sampler.core.ui.extension.observeOnCreated
import ru.chernakov.sampler.core.ui.presentation.observer.LoadingObserver
import ru.chernakov.sampler.core.ui.presentation.viewmodel.BaseViewModel

abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {
    abstract val viewModel: BaseViewModel?
    private var runOnResume: Runnable? = null
    private var isAfterOnSavedState: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isAfterOnSavedState = false
        viewModel?.let {
            setupErrorHandling(it.error)
            setupLoading(it.loading)
        }
    }

    private fun setupErrorHandling(errorState: StateFlow<Throwable?>) {
        createErrorObserver()?.let { observer ->
            errorState.observeOnCreated(lifecycleScope) { error ->
                observer.onChanged(error)
            }
        }
    }

    private fun setupLoading(loadingState: StateFlow<Boolean>) {
        createLoadingObserver().let { observer ->
            loadingState.observeOnCreated(lifecycleScope) {
                observer.onChanged(it)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        runOnResume?.run()
        runOnResume = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        isAfterOnSavedState = true
    }

    fun postOnResume(run: Runnable) {
        if (isAfterOnSavedState) {
            runOnResume = run
        } else {
            run.run()
        }
    }

    protected open fun createLoadingObserver(): Observer<Boolean?> = LoadingObserver(null)

    protected open fun createErrorObserver(): Observer<Throwable?>? = null
}
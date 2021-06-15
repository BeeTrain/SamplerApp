package ru.chernakov.sampler.coreui.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import ru.chernakov.sampler.coreui.presentation.observer.LoadingObserver
import ru.chernakov.sampler.coreui.presentation.viewmodel.BaseViewModel

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

    private fun setupErrorHandling(errorLiveData: LiveData<Throwable?>) {
        createErrorObserver()?.let {
            errorLiveData.observe(viewLifecycleOwner, it)
        }
    }

    private fun setupLoading(loadingLiveData: LiveData<Boolean>) {
        createLoadingObserver().let {
            loadingLiveData.observe(viewLifecycleOwner, it)
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
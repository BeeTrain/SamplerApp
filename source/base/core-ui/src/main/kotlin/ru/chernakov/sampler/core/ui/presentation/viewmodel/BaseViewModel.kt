package ru.chernakov.sampler.core.ui.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    open val error = MutableStateFlow<Throwable?>(null)

    open val loading = MutableStateFlow(false)

    private fun createExceptionHandler(
        error: MutableStateFlow<Throwable?>?,
        exceptionBlock: ((Throwable) -> Unit)? = null
    ): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, throwable ->
            exceptionBlock?.invoke(throwable)
            error?.value = throwable
        }
    }

    protected fun launchLoadingErrorJob(
        error: MutableStateFlow<Throwable?>? = this.error,
        loading: MutableStateFlow<Boolean>? = this.loading,
        onError: ((Throwable) -> Unit)? = null,
        block: suspend () -> Unit
    ): Job {
        return viewModelScope.launch(createExceptionHandler(error, onError)) {
            try {
                error?.value = null
                loading?.value = true
                block()
            } finally {
                loading?.value = false
            }
        }
    }
}
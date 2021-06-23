package ru.chernakov.sampler.core.ui.extension

import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

fun <T> Flow<T>.launchWhenCreated(lifecycleScope: LifecycleCoroutineScope) {
    lifecycleScope.launchWhenCreated { this@launchWhenCreated.collect() }
}

fun <T> Flow<T>.launchWhenStarted(lifecycleScope: LifecycleCoroutineScope) {
    lifecycleScope.launchWhenStarted { this@launchWhenStarted.collect() }
}

fun <T> Flow<T>.launchWhenResumed(lifecycleScope: LifecycleCoroutineScope) {
    lifecycleScope.launchWhenResumed { this@launchWhenResumed.collect() }
}

fun <T> Flow<T>.observeOnCreated(lifecycleScope: LifecycleCoroutineScope, action: suspend (T) -> Unit) {
    onEach { action(it) }
        .launchWhenCreated(lifecycleScope)
}

fun <T> Flow<T>.observeOnStarted(lifecycleScope: LifecycleCoroutineScope, action: suspend (T) -> Unit) {
    onEach { action(it) }
        .launchWhenStarted(lifecycleScope)
}

fun <T> Flow<T>.observeOnResumed(lifecycleScope: LifecycleCoroutineScope, action: suspend (T) -> Unit) {
    onEach { action(it) }
        .launchWhenResumed(lifecycleScope)
}
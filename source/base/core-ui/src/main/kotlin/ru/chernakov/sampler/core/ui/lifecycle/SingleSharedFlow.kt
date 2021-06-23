package ru.chernakov.sampler.core.ui.lifecycle

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect

class SingleSharedFlow<T> : MutableSharedFlow<T> {

    private val mutableSharedFlow = MutableSharedFlow<T>(replay = 1)

    override val replayCache: List<T>
        get() = mutableSharedFlow.replayCache
    override val subscriptionCount: StateFlow<Int>
        get() = mutableSharedFlow.subscriptionCount

    @ExperimentalCoroutinesApi
    @InternalCoroutinesApi
    override suspend fun collect(collector: FlowCollector<T>) {
        mutableSharedFlow.collect {
            try {
                collector.emit(it)
            } catch (e: Exception) {
                throw e
            } finally {
                mutableSharedFlow.resetReplayCache()
            }
        }
    }

    override suspend fun emit(value: T) = mutableSharedFlow.emit(value)

    @ExperimentalCoroutinesApi
    override fun resetReplayCache() = mutableSharedFlow.resetReplayCache()

    override fun tryEmit(value: T) = mutableSharedFlow.tryEmit(value)

}
package ru.chernakov.sampler.coreui.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import ru.chernakov.sampler.coreui.util.lifecycle.SafeObserver

fun <T> LiveData<T?>.observeNullable(owner: LifecycleOwner, observer: (T?) -> Unit) {
    observe(owner, { observer(it) })
}

fun <T> LiveData<T?>.observeSafe(owner: LifecycleOwner, observer: (T) -> Unit) {
    observe(owner, SafeObserver { observer(it) })
}

fun <A, B, R> zipLiveData(
    a: LiveData<A>,
    b: LiveData<B>,
    block: (A, B) -> R
): LiveData<R> {
    return MediatorLiveData<R>().apply {
        var lastA: A? = null
        var lastB: B? = null

        fun update() {
            val localLastA = lastA
            val localLastB = lastB
            if (localLastA != null && localLastB != null) {
                this.value = block.invoke(localLastA, localLastB)
                lastA = null
                lastB = null
            }
        }

        addSource(a) {
            lastA = it
            update()
        }
        addSource(b) {
            lastB = it
            update()
        }
    }
}

fun <A, B, R> LiveData<A>.zipWith(b: LiveData<B>, block: (A, B) -> R): LiveData<R> =
    zipLiveData(this, b, block)
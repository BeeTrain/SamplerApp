package ru.chernakov.sampler

import android.app.Application
import ru.chernakov.sampler.coreui.util.lifecycle.LifecycleCallback
import timber.log.Timber

class SamplerApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        LifecycleCallback.register(this)
    }
}
package ru.chernakov.sampler

import android.app.Application
import ru.chernakov.sampler.core.util.lifecycle.Lifecycler
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        Lifecycler.register(this)
    }
}
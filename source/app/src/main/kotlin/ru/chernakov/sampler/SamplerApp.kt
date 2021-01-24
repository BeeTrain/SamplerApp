package ru.chernakov.sampler

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.chernakov.sampler.app.di.ApplicationModule
import ru.chernakov.sampler.coreui.util.lifecycle.LifecycleCallback
import timber.log.Timber

class SamplerApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        initKoin()
        LifecycleCallback.register(this)
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@SamplerApp)
            modules(ApplicationModule.modules)
        }
    }
}
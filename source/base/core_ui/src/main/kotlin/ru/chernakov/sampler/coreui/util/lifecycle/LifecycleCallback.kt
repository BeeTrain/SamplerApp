package ru.chernakov.sampler.coreui.util.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle

open class LifecycleCallback : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        // Do nothing.
    }

    override fun onActivityStarted(activity: Activity) {
        started++
    }

    override fun onActivityResumed(activity: Activity) {
        // Do nothing.
    }

    override fun onActivityPaused(activity: Activity) {
        // Do nothing.
    }

    override fun onActivityStopped(activity: Activity) {
        stopped++
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        // Do nothing.
    }

    override fun onActivityDestroyed(activity: Activity) {
        // Do nothing.
    }

    companion object {
        private var started: Int = 0
        private var stopped: Int = 0

        @JvmStatic
        fun register(app: Application) {
            app.registerActivityLifecycleCallbacks(LifecycleCallback())
        }

        val isApplicationVisible: Boolean
            get() = started > stopped
    }
}
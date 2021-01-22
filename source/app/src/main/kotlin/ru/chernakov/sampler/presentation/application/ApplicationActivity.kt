package ru.chernakov.sampler.presentation.application

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import ru.chernakov.sampler.R
import ru.chernakov.sampler.coreui.presentation.activity.BaseActivity

class ApplicationActivity : BaseActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
    }
}
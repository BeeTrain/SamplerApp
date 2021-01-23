package ru.chernakov.sampler.presentation.application

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.chernakov.sampler.R
import ru.chernakov.sampler.app.navigation.AppNavigator
import ru.chernakov.sampler.coreui.presentation.activity.BaseActivity

class ApplicationActivity : BaseActivity(R.layout.activity_application) {
    private val navigator: AppNavigator by inject()
    private lateinit var navController: NavController

    private val viewModel: ApplicationViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navController = findNavController(R.id.application_nav_host)
    }

    override fun onStart() {
        super.onStart()
        navigator.bindAppController(navController)
    }

    override fun onStop() {
        super.onStop()
        navigator.unbindAppController()
    }
}
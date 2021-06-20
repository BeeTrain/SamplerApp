package ru.chernakov.sampler.presentation.application

import android.os.Bundle
import androidx.core.view.updatePadding
import androidx.navigation.NavController
import androidx.navigation.findNavController
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.chernakov.sampler.R
import ru.chernakov.sampler.core.ui.extension.bind
import ru.chernakov.sampler.core.ui.extension.doOnApplyWindowInsets
import ru.chernakov.sampler.core.ui.extension.pxToDp
import ru.chernakov.sampler.core.ui.presentation.activity.BaseActivity
import ru.chernakov.sampler.navigation.navigator.AppNavigator
import ru.chernakov.sampler.widget.container.constraint.ConstraintContainer

class ApplicationActivity : BaseActivity(R.layout.activity_application) {
    private val viewModel: ApplicationViewModel by viewModel()
    private val navigator: AppNavigator by inject()

    private lateinit var navController: NavController

    private val rootContainer by bind<ConstraintContainer>(R.id.root_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_SamplerApp)
        super.onCreate(savedInstanceState)

        rootContainer.doOnApplyWindowInsets { view, insets, _ ->
            val topInset = insets.systemWindowInsetTop
            val bottomInset = insets.systemWindowInsetBottom

            view.updatePadding(top = topInset, bottom = bottomInset)

            viewModel.topInsetLiveData.postValue(pxToDp(topInset.toFloat()))
            viewModel.bottomInsetLiveData.postValue(pxToDp(bottomInset.toFloat()))
            insets.consumeSystemWindowInsets()
        }

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
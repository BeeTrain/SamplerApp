package ru.chernakov.sampler.mainprofile.presentation

import android.os.Bundle
import android.view.View
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.chernakov.sampler.coreui.extension.findView
import ru.chernakov.sampler.coreui.extension.setOnClickWithDelayListener
import ru.chernakov.sampler.coreui.extension.startAnimation
import ru.chernakov.sampler.coreui.presentation.fragment.BaseFragment
import ru.chernakov.sampler.mainprofile.R
import ru.chernakov.sampler.mainprofile.app.navigation.ProfileNavigator
import ru.chernakov.sampler.widget.image.ImageView

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {
    private val navigator: ProfileNavigator by inject()
    override val viewModel: ProfileViewModel by viewModel()

    private val settingsIcon by findView<ImageView>(R.id.profile_settings)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingsIcon.setOnClickWithDelayListener {
            it.startAnimation(R.anim.rotation)
            navigator.fromProfileToSettings()
        }
    }
}
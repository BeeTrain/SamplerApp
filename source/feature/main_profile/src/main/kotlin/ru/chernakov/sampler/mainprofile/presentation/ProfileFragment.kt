package ru.chernakov.sampler.mainprofile.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import ru.chernakov.sampler.coreui.extension.findView
import ru.chernakov.sampler.coreui.presentation.fragment.BaseFragment
import ru.chernakov.sampler.coreui.presentation.viewmodel.BaseViewModel
import ru.chernakov.sampler.mainprofile.R
import ru.chernakov.sampler.widget.list.ToggleListItem

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {
    override val viewModel = BaseViewModel()

    private val themeModeItem by findView<ToggleListItem>(R.id.profile_dark_theme_item)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        themeModeItem.apply {
            title = "Enable dark theme"
            subtitle = "Instantly enabling dark theme"
            checkedChangeListener = { _, isChecked ->
                (activity as? AppCompatActivity)?.delegate?.localNightMode = if (isChecked) {
                    AppCompatDelegate.MODE_NIGHT_YES
                } else {
                    AppCompatDelegate.MODE_NIGHT_NO
                }
            }
        }
    }
}
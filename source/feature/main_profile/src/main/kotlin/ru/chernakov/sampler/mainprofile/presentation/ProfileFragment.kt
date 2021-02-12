package ru.chernakov.sampler.mainprofile.presentation

import android.os.Bundle
import android.view.View
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.chernakov.sampler.coreui.extension.findView
import ru.chernakov.sampler.coreui.presentation.fragment.BaseFragment
import ru.chernakov.sampler.mainprofile.R
import ru.chernakov.sampler.mainprofile.domain.model.AppTheme
import ru.chernakov.sampler.widget.list.ToggleListItem

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {
    override val viewModel: ProfileViewModel by viewModel()

    private val themeModeItem by findView<ToggleListItem>(R.id.profile_dark_theme_item)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {
            appThemeLiveData.observe(viewLifecycleOwner) { theme ->
                themeModeItem.isChecked = theme == AppTheme.DARK
            }
        }

        themeModeItem.apply {
            title = getString(R.string.title_setting_app_theme)
            checkedChangeListener = { _, isChecked ->
                viewModel.switchAppTheme(isChecked)
            }
        }
    }
}
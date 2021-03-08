package ru.chernakov.sampler.settings.presentation

import android.os.Bundle
import android.view.View
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.chernakov.sampler.coreui.extension.findView
import ru.chernakov.sampler.coreui.presentation.fragment.BaseFragment
import ru.chernakov.sampler.settings.R
import ru.chernakov.sampler.settings.app.SettingsNavigator
import ru.chernakov.sampler.settings.domain.model.AppTheme
import ru.chernakov.sampler.widget.appbar.Toolbar
import ru.chernakov.sampler.widget.list.ToggleListItem

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {
    private val navigator: SettingsNavigator by inject()
    override val viewModel: SettingsViewModel by viewModel()

    private val themeModeItem by findView<ToggleListItem>(R.id.settings_dark_theme_item)
    private val toolbar by findView<Toolbar>(R.id.settings_toolbar)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            appThemeLiveData.observe(viewLifecycleOwner) { theme ->
                themeModeItem.isChecked = theme == AppTheme.DARK
            }
        }

        toolbar.apply {
            setNavigationIcon(R.drawable.ic_close)
            setNavigationOnClickListener { navigator.fromSettingsToMain() }
        }

        themeModeItem.apply {
            title = "Theme"
            label = getString(R.string.title_settings_app_theme)
            caption = "Select app theme"
            checkedChangeListener = { _, isChecked ->
                viewModel.switchAppTheme(isChecked)
            }
        }
    }
}
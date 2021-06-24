package ru.chernakov.sampler.settings.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.chernakov.sampler.core.ui.extension.findView
import ru.chernakov.sampler.core.ui.extension.observeOnCreated
import ru.chernakov.sampler.core.ui.presentation.fragment.BaseFragment
import ru.chernakov.sampler.settings.R
import ru.chernakov.sampler.settings.app.navigation.SettingsNavigator
import ru.chernakov.sampler.theme.api.model.AppTheme
import ru.chernakov.sampler.widget.appbar.Toolbar
import ru.chernakov.sampler.widget.list.ToggleListItem

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {
    private val navigator: SettingsNavigator by inject()
    override val viewModel: SettingsViewModel by viewModel()

    private val toolbar by findView<Toolbar>(R.id.settings_toolbar)
    private val themeModeItem by findView<ToggleListItem>(R.id.settings_dark_theme_item)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.appThemeState.observeOnCreated(lifecycleScope) { appTheme ->
            themeModeItem.isChecked = appTheme == AppTheme.DARK
        }

        toolbar.apply {
            setNavigationIcon(R.drawable.ic_close)
            setNavigationOnClickListener { navigator.fromSettingsToMain() }
        }

        themeModeItem.apply {
            label = getString(R.string.title_settings_app_theme)
            checkedChangeListener = { _, isChecked ->
                viewModel.switchAppTheme(isChecked)
            }
        }
    }
}
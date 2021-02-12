package ru.chernakov.sampler.main.presentation

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.chernakov.sampler.coreui.extension.findView
import ru.chernakov.sampler.coreui.extension.observeSafe
import ru.chernakov.sampler.coreui.presentation.fragment.BaseFragment
import ru.chernakov.sampler.main.R
import ru.chernakov.sampler.main.presentation.model.TabItem
import ru.chernakov.sampler.widget.navigation.BottomNavBar

class MainFragment : BaseFragment(R.layout.fragment_main) {
    override val viewModel: MainViewModel by viewModel()

    private val bottomNavBar by findView<BottomNavBar>(R.id.bottomNavBar)

    private val bottomNavListener = getBottomNavListener()

    private val currentTabFragment: Fragment?
        get() = childFragmentManager.fragments.firstOrNull { !it.isHidden }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.onBackPressedDispatcher?.addCallback(this) { onBackPressed() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            selectedTab.observeSafe(viewLifecycleOwner) { selectTab(it) }
            popTabBackStackEvent.observeSafe(viewLifecycleOwner) { bottomNavBar.selectedItemId = it }
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        setSelectedTabIcon()
    }

    private fun onBackPressed() {
        if (viewModel.popTabBackStack()) return
        activity?.finish()
    }

    private fun selectTab(tabItem: TabItem) {
        val currentFragment = currentTabFragment
        val newFragment = childFragmentManager.findFragmentByTag(tabItem.tag)

        if (currentFragment != null && newFragment != null && currentFragment == newFragment) return

        childFragmentManager.beginTransaction().apply {
            if (newFragment == null) {
                add(R.id.mainFlowContainer, tabItem.getFragment(), tabItem.tag)
            }

            currentFragment?.let {
                hide(it)
                setMaxLifecycle(it, Lifecycle.State.CREATED)
            }
            newFragment?.let {
                show(it)
                setMaxLifecycle(it, Lifecycle.State.RESUMED)
            }
        }.commitNow()
    }

    private fun setSelectedTabIcon() {
        with(bottomNavBar) {
            setOnNavigationItemSelectedListener(null)
            selectedItemId = viewModel.getSelectedItemId()
            setOnNavigationItemSelectedListener(bottomNavListener)
        }
    }

    private fun getBottomNavListener() = object : BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            if (childFragmentManager.isStateSaved) return false

            if (bottomNavBar.selectedItemId != item.itemId) return viewModel.onTabClick(item.itemId)

            return true
        }
    }
}
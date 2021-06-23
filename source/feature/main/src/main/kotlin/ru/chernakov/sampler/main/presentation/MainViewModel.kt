package ru.chernakov.sampler.main.presentation

import java.util.ArrayDeque
import java.util.Deque
import kotlinx.coroutines.flow.MutableStateFlow
import ru.chernakov.sampler.core.ui.lifecycle.SingleSharedFlow
import ru.chernakov.sampler.core.ui.presentation.viewmodel.BaseViewModel
import ru.chernakov.sampler.main.presentation.model.FeedTab
import ru.chernakov.sampler.main.presentation.model.ProfileTab
import ru.chernakov.sampler.main.presentation.model.ServicesTab
import ru.chernakov.sampler.main.presentation.model.TabItem

class MainViewModel : BaseViewModel() {

    val selectedTab = MutableStateFlow<TabItem>(getDefaultTab())

    val popTabBackStackEvent = SingleSharedFlow<Int>()

    private val tabs: List<TabItem> = createTabs()
    private val tabStack: Deque<TabItem> = ArrayDeque()
    private val defaultTab = getDefaultTab()

    private var needToPopBackStack = false

    fun onTabClick(itemId: Int): Boolean {
        val tabItem = tabs.firstOrNull { it.itemId == itemId } ?: return false
        if (needToPopBackStack) {
            needToPopBackStack = false
        } else {
            addTabToBackStack(selectedTab.value)
        }
        selectedTab.value = tabItem
        return true
    }

    fun popTabBackStack(): Boolean {
        val tail = tabStack.pollLast()

        if (tail != null) {
            needToPopBackStack = true
            popTabBackStackEvent.tryEmit(tail.itemId)

            return true
        }

        if (selectedTab.value != defaultTab) {
            needToPopBackStack = true
            popTabBackStackEvent.tryEmit(defaultTab.itemId)

            return true
        }

        return false
    }

    private fun addTabToBackStack(tab: TabItem?) {
        if (tab == null) return

        tabStack.removeLastOccurrence(tab)
        tabStack.addLast(tab)
    }

    fun getSelectedItemId() = selectedTab.value.itemId

    private fun createTabs(): List<TabItem> {
        return listOf(
            FeedTab(),
            ServicesTab(),
            ProfileTab()
        )
    }

    private fun getDefaultTab() = FeedTab()
}
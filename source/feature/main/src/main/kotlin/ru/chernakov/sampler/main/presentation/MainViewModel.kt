package ru.chernakov.sampler.main.presentation

import androidx.lifecycle.MutableLiveData
import java.util.*
import ru.chernakov.sampler.core.ui.presentation.viewmodel.BaseViewModel
import ru.chernakov.sampler.core.ui.util.lifecycle.SingleLiveEvent
import ru.chernakov.sampler.main.presentation.model.FeedTab
import ru.chernakov.sampler.main.presentation.model.ProfileTab
import ru.chernakov.sampler.main.presentation.model.ServicesTab
import ru.chernakov.sampler.main.presentation.model.TabItem

class MainViewModel : BaseViewModel() {
    val selectedTab = MutableLiveData<TabItem>(getDefaultTab())
    val popTabBackStackEvent = SingleLiveEvent<Int>()

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
        selectedTab.postValue(tabItem)
        return true
    }

    fun popTabBackStack(): Boolean {
        val tail = tabStack.pollLast()

        if (tail != null) {
            needToPopBackStack = true
            popTabBackStackEvent.postValue(tail.itemId)
            return true
        }

        if (selectedTab.value != defaultTab) {
            needToPopBackStack = true
            popTabBackStackEvent.postValue(defaultTab.itemId)
            return true
        }

        return false
    }

    private fun addTabToBackStack(tab: TabItem?) {
        if (tab == null) return

        tabStack.removeLastOccurrence(tab)
        tabStack.addLast(tab)
    }

    fun getSelectedItemId() = selectedTab.value?.itemId ?: getDefaultTab().itemId

    private fun createTabs(): List<TabItem> {
        return listOf(
            FeedTab(),
            ServicesTab(),
            ProfileTab()
        )
    }

    private fun getDefaultTab() = FeedTab()
}
package ru.chernakov.sampler.main.presentation.model

import androidx.fragment.app.Fragment
import ru.chernakov.sampler.main.R
import ru.chernakov.sampler.mainfeed.presentation.FeedFlowFragment
import ru.chernakov.sampler.mainprofile.presentation.ProfileFlowFragment
import ru.chernakov.sampler.mainservices.presentation.ServicesFlowFragment

sealed class TabItem {
    abstract fun getFragment(): Fragment
    abstract val tag: String
    abstract val itemId: Int
}

class FeedTab : TabItem() {
    override val tag: String = FeedTab::class.java.name
    override val itemId: Int = R.id.feed
    override fun getFragment() = FeedFlowFragment()
}

class ServicesTab : TabItem() {
    override val tag: String = ServicesTab::class.java.name
    override val itemId: Int = R.id.services
    override fun getFragment() = ServicesFlowFragment()
}

class ProfileTab : TabItem() {
    override val tag: String = ProfileTab::class.java.name
    override val itemId: Int = R.id.profile
    override fun getFragment() = ProfileFlowFragment()
}
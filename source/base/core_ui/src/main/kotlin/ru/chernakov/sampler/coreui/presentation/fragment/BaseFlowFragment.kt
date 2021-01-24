package ru.chernakov.sampler.coreui.presentation.fragment

import android.os.Bundle
import androidx.activity.addCallback
import androidx.annotation.NavigationRes
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment

abstract class BaseFlowFragment : NavHostFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavGraph()

        requireActivity().onBackPressedDispatcher.addCallback(this) { onBackPressed() }
    }

    open fun setupNavGraph() {
        val navInflater = navController.navInflater
        val graph = navInflater.inflate(getGraphResId())
        val startDestArgs = onCreateGraph(graph)
        navController.setGraph(graph, startDestArgs)
    }

    @NavigationRes
    abstract fun getGraphResId(): Int

    abstract fun onBackPressed()

    open fun onCreateGraph(graph: NavGraph): Bundle? = null
}
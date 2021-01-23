package ru.chernakov.sampler.app.navigation

import androidx.navigation.NavController

interface AppNavigator {
    fun bindAppController(navController: NavController)
    fun unbindAppController()
}
package ru.chernakov.sampler.navigation.navigator

import androidx.navigation.NavController

interface AppNavigator {

    fun bindAppController(navController: NavController)

    fun unbindAppController()
}
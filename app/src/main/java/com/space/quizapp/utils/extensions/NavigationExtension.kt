package com.space.quizapp.utils.extensions

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation

fun navigateSafe(navController: NavController, action: NavDirections) {
    navController.currentDestination?.getAction(action.actionId)?.let {
        try {
            navController.navigate(action)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }
}

fun popBackStack(it: View) {
    val navController = Navigation.findNavController(it)
    try {
        navController.popBackStack()
    } catch (e: IllegalArgumentException) {
        e.printStackTrace()
    }
}
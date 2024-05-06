package com.github.hemoptysisheart.sample.ui.navigation

import android.util.Log
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.github.hemoptysisheart.ui.navigation.destination.BaseNavigator
import com.github.hemoptysisheart.ui.navigation.destination.Destination
import com.github.hemoptysisheart.ui.navigation.destination.Navigator

class SelectSizeNavigator(
    private val base: BaseNavigator
) : Navigator by base {
    companion object : Destination {
        private const val TAG = "SelectSizeNavigator"

        override val id = "select-size"
        override val arguments: List<NamedNavArgument> = emptyList()
        override val deepLinks: List<NavDeepLink> = emptyList()

        override fun route(vararg arguments: Any): String {
            if (arguments.isNotEmpty()) {
                throw IllegalArgumentException("does not accept any arguments.")
            } else {
                return id
            }
        }
    }

    override val destination = Companion

    fun maze() {
        Log.d(TAG, "#maze called.")
        base.navHostController.navigate(MazeNavigator.id)
    }

    fun history() {
        Log.d(TAG, "#history called.")

        base.navHostController.navigate(HistoryNavigator.id) {
            this.popUpTo(base.navHostController.graph.findStartDestination().route!!) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

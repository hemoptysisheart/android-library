package com.github.hemoptysisheart.sample.ui.navigation

import android.util.Log
import androidx.navigation.NavDeepLink
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.github.hemoptysisheart.ui.navigation.destination.BaseNavigator
import com.github.hemoptysisheart.ui.navigation.destination.Destination
import com.github.hemoptysisheart.ui.navigation.destination.Navigator

class MazeNavigator(
    private val base: BaseNavigator
) : Navigator by base {
    companion object : Destination {
        private const val TAG = "MazeNavigator"

        const val ARG_WIDTH = "width"
        const val ARG_HEIGHT = "height"

        override val id = "maze/{$ARG_WIDTH}x{$ARG_HEIGHT}"

        override val arguments = listOf(
            navArgument(ARG_WIDTH) {
                type = NavType.IntType
                nullable = false
            },
            navArgument(ARG_HEIGHT) {
                type = NavType.IntType
                nullable = false
            }
        )

        override val deepLinks: List<NavDeepLink> = emptyList()

        /**
         * TODO 미로 크기 지정하기.
         */
        override fun route(vararg arguments: Any): String {
            Log.d(TAG, "#route args : arguments=${arguments.toList()}")
            when (arguments.size) {
                2 ->
                    return route(arguments[0] as Int, arguments[1] as Int)

                else ->
                    throw IllegalArgumentException("Invalid arguments : $arguments")
            }
        }

        fun route(width: Int, height: Int): String {
            Log.d(TAG, "#route args : width=$width, height=$height")
            return "maze/${width}x${height}"
        }
    }

    override val destination = Companion
}

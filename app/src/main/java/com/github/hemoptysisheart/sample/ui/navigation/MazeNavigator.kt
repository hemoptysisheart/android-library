package com.github.hemoptysisheart.sample.ui.navigation

import com.github.hemoptysisheart.ui.navigation.destination.BaseNavigator
import com.github.hemoptysisheart.ui.navigation.destination.Destination
import com.github.hemoptysisheart.ui.navigation.destination.Navigator

class MazeNavigator(
    private val base: BaseNavigator
) : Navigator by base {
    companion object : Destination {
        override val id = "maze"

        /**
         * TODO 미로 크기 지정하기.
         */
        override fun route(vararg arguments: Any): String {
            return id
        }
    }

    override val destination = Companion
}

package com.github.hemoptysisheart.sample.ui.navigation

import android.util.Log
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import com.github.hemoptysisheart.ui.navigation.destination.BaseNavigator
import com.github.hemoptysisheart.ui.navigation.destination.Destination
import com.github.hemoptysisheart.ui.navigation.destination.Navigator

class SplashNavigator(
    private val base: BaseNavigator
) : Navigator by base {
    companion object : Destination {
        private const val TAG = "SplashNavigator"

        override val id = "splash"
        override val arguments: List<NamedNavArgument> = emptyList()
        override val deepLinks: List<NavDeepLink> = emptyList()

        override fun route(vararg arguments: Any) = if (arguments.isNotEmpty()) {
            throw IllegalArgumentException("does not accept any arguments.")
        } else {
            id
        }
    }

    override val destination = Companion

    /**
     * [ com.github.hemoptysisheart.sample.ui.page.SelectSizePage]로 이동한다.
     */
    fun selectSize() {
        Log.d(TAG, "#selectSize called.")

        base.navHostController.navigate(SelectSizeNavigator.id) {
            popUpTo(destination.id) {
                inclusive = true
            }
        }
    }
}

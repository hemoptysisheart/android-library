package com.github.hemoptysisheart.ui.navigation.destination

import android.app.Activity
import android.util.Log
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.navigation.NavHostController

/**
 * [Navigator]의 공통 기능을 제공하는 클래스.
 */
class BaseNavigator(
    /**
     * 이 [Navigator]가 속한 [Activity].
     */
    val activity: Activity,
    val navHostController: NavHostController,
    val startDestination: Destination,
    val softwareKeyboardController: SoftwareKeyboardController? = null
) : Navigator {
    companion object {
        private const val TAG = "BaseNavigator"
    }

    override val destination: Destination
        get() = throw UnsupportedOperationException()

    override fun back() {
        Log.d(TAG, "#back called.")

        navHostController.popBackStack()
    }

    override fun keyboard(show: Boolean) {
        Log.d(TAG, "#keyboard args : show=$show")

        if (show) {
            softwareKeyboardController?.show()
        } else {
            softwareKeyboardController?.hide()
        }
    }

    override fun equals(other: Any?): Boolean = this === other || (
            other is BaseNavigator &&
                    activity == other.activity &&
                    navHostController == other.navHostController &&
                    softwareKeyboardController == other.softwareKeyboardController
            )

    override fun hashCode(): Int {
        var result = activity.hashCode()
        result = 31 * result + navHostController.hashCode()
        result = 31 * result + (softwareKeyboardController?.hashCode() ?: 0)
        return result
    }

    override fun toString() = listOf(
        "activity=$activity",
        "navHostController=$navHostController",
        "startDestination=$startDestination",
        "softwareKeyboardController=$softwareKeyboardController"
    ).joinToString(", ", "BaseNavigator(", ")")
}

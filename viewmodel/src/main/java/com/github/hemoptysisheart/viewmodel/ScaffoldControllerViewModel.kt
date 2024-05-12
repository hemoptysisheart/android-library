package com.github.hemoptysisheart.viewmodel

import android.util.Log
import com.github.hemoptysisheart.statepump.ScaffoldPump
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.CoroutineContext

/**
 * 앱 전체적인 스캐폴드 레이아웃을 사용할 때, 스캐폴드 자체를 제어할 때 사용하는 전용 뷰모델.
 *
 * @see com.github.hemoptysisheart.ui.navigation.compose.ScaffoldController
 */
class ScaffoldControllerViewModel(
    fallbackCoroutineExceptionHandler: CoroutineExceptionHandler = object : CoroutineExceptionHandler {
        override val key = CoroutineExceptionHandler.Key

        override fun handleException(context: CoroutineContext, exception: Throwable) {
            Log.w(tag, "#handleException : ${exception.message}", exception)
        }
    },
    scaffoldPump: ScaffoldPump
) : BaseViewModel(tag, fallbackCoroutineExceptionHandler) {
    companion object {
        private val tag = ScaffoldControllerViewModel::class.java.simpleName
    }


    val topBar = scaffoldPump.topBar

    val bottomBar = scaffoldPump.bottomBar

    override fun toString() = listOf(
        "topBar=$topBar",
        "bottomBar=$bottomBar"
    ).joinToString(", ", "$tag(${super.toString()}, ", ")")
}

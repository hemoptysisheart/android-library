package com.github.hemoptysisheart.sample.model

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.CoroutineContext

class FallbackCoroutineExceptionHandler(
    private val fallbackExceptionHandler: FallbackExceptionHandler
) : CoroutineExceptionHandler {
    companion object {
        private const val TAG = "FallbackCoroutineExceptionHandler"
    }

    override val key = CoroutineExceptionHandler.Key

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        Log.w(TAG, "#handleException args : context=$context, exception=$exception")
        fallbackExceptionHandler.uncaughtException(Thread.currentThread(), exception)
    }

    override fun toString() = listOf(
        "fallbackExceptionHandler=$fallbackExceptionHandler"
    ).joinToString(", ", "$TAG(", ")")
}

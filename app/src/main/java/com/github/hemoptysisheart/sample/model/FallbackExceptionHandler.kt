package com.github.hemoptysisheart.sample.model

import android.util.Log

/**
 * `try - catch` 처리가 없는 예외를 처리하는 기본 핸들러.
 */
class FallbackExceptionHandler : Thread.UncaughtExceptionHandler {
    companion object {
        private const val TAG = "DefaultExceptionHandler"
    }

    override fun uncaughtException(thread: Thread, throwable: Throwable) {
        Log.w(TAG, "#uncaughtException : thread=$thread, throwable=$throwable", throwable)
    }
}

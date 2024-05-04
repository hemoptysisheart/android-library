package com.github.hemoptysisheart.sample.app

import android.app.Application
import android.util.Log
import com.github.hemoptysisheart.sample.model.ApplicationCoroutineScope
import com.github.hemoptysisheart.sample.model.FallbackExceptionHandler
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class AndroidLibraryApplication : Application() {
    companion object {
        private const val TAG = "AndroidLibraryApplication"
    }

    @Inject
    lateinit var fallbackExceptionHandler: FallbackExceptionHandler

    @Inject
    lateinit var applicationCoroutineScope: ApplicationCoroutineScope

    override fun onCreate() {
        Log.d(TAG, "#onCreate called.")
        super.onCreate()

        Thread.setDefaultUncaughtExceptionHandler(fallbackExceptionHandler)

        // 테스트용 예외 발생 샘플.
        applicationCoroutineScope.launch {
            delay(1000)
            throw RuntimeException("Test exception")
        }
    }

    override fun onTerminate() {
        Log.d(TAG, "#onTerminate called.")
        super.onTerminate()
    }
}

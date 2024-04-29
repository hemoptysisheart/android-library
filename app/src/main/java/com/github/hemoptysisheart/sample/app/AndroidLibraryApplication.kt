package com.github.hemoptysisheart.sample.app

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AndroidLibraryApplication : Application() {
    companion object {
        private const val TAG = "AndroidLibraryApplication"
    }

    override fun onCreate() {
        Log.d(TAG, "#onCreate called.")
        super.onCreate()
    }

    override fun onTerminate() {
        Log.d(TAG, "#onTerminate called.")
        super.onTerminate()
    }
}

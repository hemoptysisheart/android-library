package com.github.hemoptysisheart.sample.app

import android.app.Application
import android.util.Log

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

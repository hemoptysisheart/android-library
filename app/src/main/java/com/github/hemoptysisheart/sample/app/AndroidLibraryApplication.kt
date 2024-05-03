package com.github.hemoptysisheart.sample.app

import android.app.Application
import android.util.Log
import com.github.hemoptysisheart.sample.model.SampleModel
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class AndroidLibraryApplication : Application() {
    companion object {
        private const val TAG = "AndroidLibraryApplication"
    }

    @Inject
    lateinit var sampleModel: SampleModel

    override fun onCreate() {
        Log.d(TAG, "#onCreate called.")
        super.onCreate()

        Log.i(TAG, "#onCreate : sampleModel=$sampleModel")
    }

    override fun onTerminate() {
        Log.d(TAG, "#onTerminate called.")
        super.onTerminate()
    }
}

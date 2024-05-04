package com.github.hemoptysisheart.sample.app

import android.util.Log
import com.github.hemoptysisheart.sample.model.FallbackExceptionHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleConfig {
    companion object {
        private const val TAG = "ModuleConfig"
    }

    @Provides
    @Singleton
    fun provideFallbackExceptionHandler(): FallbackExceptionHandler {
        val handler = FallbackExceptionHandler()
        Log.i(TAG, "#provideFallbackExceptionHandler : handler=$handler")
        return handler
    }
}

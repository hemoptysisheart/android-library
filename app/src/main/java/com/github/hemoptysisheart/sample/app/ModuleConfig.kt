package com.github.hemoptysisheart.sample.app

import android.util.Log
import com.github.hemoptysisheart.sample.model.ApplicationCoroutineScope
import com.github.hemoptysisheart.sample.model.FallbackCoroutineExceptionHandler
import com.github.hemoptysisheart.sample.model.FallbackExceptionHandler
import com.github.hemoptysisheart.sample.model.FallbackViewModelScopeExceptionHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
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

    @Provides
    @Singleton
    fun provideFallbackCoroutineExceptionHandler(
        fallbackExceptionHandler: FallbackExceptionHandler
    ): FallbackCoroutineExceptionHandler {
        val handler = FallbackCoroutineExceptionHandler(fallbackExceptionHandler)
        Log.i(TAG, "#provideFallbackCoroutineExceptionHandler : handler=$handler")
        return handler
    }

    @Provides
    @Singleton
    fun provideFallbackViewModelScopeExceptionHandler(): FallbackViewModelScopeExceptionHandler {
        val handler = FallbackViewModelScopeExceptionHandler()
        Log.i(TAG, "#provideFallbackViewModelScopeExceptionHandler : handler=$handler")
        return handler
    }

    @Provides
    @Singleton
    fun provideApplicationCoroutineScope(
        fallbackCoroutineExceptionHandler: FallbackCoroutineExceptionHandler
    ): ApplicationCoroutineScope {
        val scope = ApplicationCoroutineScope(
            CoroutineScope(SupervisorJob() + fallbackCoroutineExceptionHandler)
        )
        Log.i(TAG, "#provideApplicationCoroutineScope : scope=$scope")
        return scope
    }
}

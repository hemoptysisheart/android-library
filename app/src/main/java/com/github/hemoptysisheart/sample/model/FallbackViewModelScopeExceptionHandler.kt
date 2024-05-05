package com.github.hemoptysisheart.sample.model

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.CoroutineContext

/**
 * UI 인터랙션으로 실행한 [viewModelScope]에서 발생한 예외를 마지막으로 처리하는 공용 [CoroutineExceptionHandler].
 */
class FallbackViewModelScopeExceptionHandler : CoroutineExceptionHandler {
    companion object {
        private val TAG = "FallbackViewModelScopeExceptionHandler"
    }

    override val key = CoroutineExceptionHandler.Key

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        Log.w(TAG, "#handleException args : context=$context, exception=$exception", exception)

        // TODO UI로 사용자에세 에러 메시지 표시.
    }
}

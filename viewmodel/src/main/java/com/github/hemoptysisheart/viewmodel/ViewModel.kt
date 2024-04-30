package com.github.hemoptysisheart.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * [androidx.lifecycle.ViewModel]에 일부 기능을 추가한 클래스.
 */
open class ViewModel(
    protected val tag: String
) : androidx.lifecycle.ViewModel() {
    /**
     * [androidx.lifecycle.viewModelScope]를 이용하여 새 코루틴으로 [block]을 실행한다.
     *
     * @param exceptionHandler 예외 처리기.
     * @param context 사용할 [CoroutineContext].
     * @param start [CoroutineStart].
     * @param block 실행할 블록.
     *
     * @return [kotlinx.coroutines.Job].
     */
    @Suppress("NOTHING_TO_INLINE")
    protected inline fun launch(
        noinline exceptionHandler: (Exception) -> Unit = { e ->
            Log.w(tag, "#exceptionHandler : ${e.message}", e)
        },
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        noinline block: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(context, start) {
        try {
            block()
        } catch (e: Exception) {
            exceptionHandler(e)
        }
    }

    /**
     * [androidx.lifecycle.viewModelScope]를 이용하여 새 코루틴으로 [block]을 실행한다.
     *
     * @param exceptionHandler 예외 처리기.
     * @param context 사용할 [CoroutineContext].
     * @param start [CoroutineStart].
     * @param block 실행할 블록.
     *
     * @return [T]의 [kotlinx.coroutines.Deferred].
     */
    protected inline fun <reified T> async(
        noinline exceptionHandler: (Exception) -> Unit = { e ->
            Log.w(tag, "#exceptionHandler : ${e.message}", e)
        },
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        noinline block: suspend CoroutineScope.() -> T
    ) = viewModelScope.async(context, start) {
        try {
            block()
        } catch (e: Exception) {
            exceptionHandler(e)
        }
    }
}

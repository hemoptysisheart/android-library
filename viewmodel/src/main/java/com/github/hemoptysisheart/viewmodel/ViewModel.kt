package com.github.hemoptysisheart.viewmodel

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.github.hemoptysisheart.ui.state.InteractionImpact
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicInteger
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * [androidx.lifecycle.ViewModel]에 일부 기능을 추가한 클래스.
 */
open class ViewModel(
    protected val tag: String
) : androidx.lifecycle.ViewModel(), DefaultLifecycleObserver {
    private val visibleImpacts = AtomicInteger(0)
    private val blockingImpacts = AtomicInteger(0)

    private val _visibleProgress = MutableStateFlow(false)
    val visibleProgress: StateFlow<Boolean> = _visibleProgress

    private val _blockingProgress = MutableStateFlow(false)
    val blockingProgress: StateFlow<Boolean> = _blockingProgress

    /**
     * [androidx.lifecycle.viewModelScope]를 이용하여 새 코루틴으로 [block]을 실행한다.
     *
     * @param impact 실행한 코루틴이 사용자 인터랙션에 미치는 영향.
     * @param exceptionHandler [block]에서 발생한 예외 처리.
     * @param context 사용할 [CoroutineContext].
     * @param start [CoroutineStart].
     * @param block 실행할 블록.
     *
     * @return [kotlinx.coroutines.Job].
     */
    protected fun launch(
        impact: InteractionImpact = InteractionImpact.NONE,
        exceptionHandler: (Exception) -> Unit = { e ->
            Log.w(tag, "#exceptionHandler : ${e.message}", e)
            throw e
        },
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job = viewModelScope.launch(context, start) {
        try {
            when (impact) {
                InteractionImpact.NONE -> {}

                InteractionImpact.VISIBLE ->
                    _visibleProgress.emit(0 < visibleImpacts.incrementAndGet())

                InteractionImpact.BLOCKING ->
                    _blockingProgress.emit(0 < blockingImpacts.incrementAndGet())
            }

            block()
        } catch (e: Exception) {
            exceptionHandler(e)
        } finally {
            when (impact) {
                InteractionImpact.NONE -> {}

                InteractionImpact.VISIBLE ->
                    _visibleProgress.emit(0 < visibleImpacts.decrementAndGet())

                InteractionImpact.BLOCKING ->
                    _blockingProgress.emit(0 < blockingImpacts.decrementAndGet())
            }
        }
    }

    /**
     * [androidx.lifecycle.viewModelScope]를 이용하여 새 코루틴으로 [block]을 실행한다.
     *
     * @param impact 실행한 코루틴이 사용자 인터랙션에 미치는 영향.
     * @param exceptionHandler [block]에서 발생한 예외 처리.
     * @param context 사용할 [CoroutineContext].
     * @param start [CoroutineStart].
     * @param block 실행할 블록.
     *
     * @return [T]의 [kotlinx.coroutines.Deferred].
     */
    protected fun <T> async(
        impact: InteractionImpact = InteractionImpact.NONE,
        exceptionHandler: (Exception) -> Unit = { e ->
            Log.w(tag, "#exceptionHandler : ${e.message}", e)
            throw e
        },
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> T
    ) = viewModelScope.async(context, start) {
        try {
            when (impact) {
                InteractionImpact.NONE -> {}

                InteractionImpact.VISIBLE ->
                    _visibleProgress.emit(0 < visibleImpacts.incrementAndGet())

                InteractionImpact.BLOCKING ->
                    _blockingProgress.emit(0 < blockingImpacts.incrementAndGet())
            }

            block()
        } catch (e: Exception) {
            exceptionHandler(e)
        } finally {
            when (impact) {
                InteractionImpact.NONE -> {}

                InteractionImpact.VISIBLE ->
                    _visibleProgress.emit(0 < visibleImpacts.decrementAndGet())

                InteractionImpact.BLOCKING ->
                    _blockingProgress.emit(0 < blockingImpacts.decrementAndGet())
            }
        }
    }

    override fun onCreate(owner: LifecycleOwner) {
        Log.d(tag, "#onCreate args : owner=$owner")
        super.onCreate(owner)
    }

    override fun onStart(owner: LifecycleOwner) {
        Log.d(tag, "#onStart : owner=$owner")
        super.onStart(owner)
    }

    override fun onResume(owner: LifecycleOwner) {
        Log.d(tag, "#onResume : owner=$owner")
        super.onResume(owner)
    }

    override fun onPause(owner: LifecycleOwner) {
        Log.d(tag, "#onPause args : owner=$owner")
        super.onPause(owner)
    }

    override fun onStop(owner: LifecycleOwner) {
        Log.d(tag, "#onStop args : owner=$owner")
        super.onStop(owner)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        Log.d(tag, "#onDestroy args : owner=$owner")
        super.onDestroy(owner)
    }

    override fun onCleared() {
        Log.d(tag, "#onCleared called.")
        super.onCleared()
    }
}

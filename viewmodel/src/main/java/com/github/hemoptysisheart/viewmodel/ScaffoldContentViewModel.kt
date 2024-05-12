package com.github.hemoptysisheart.viewmodel

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.github.hemoptysisheart.statepump.ScaffoldPump
import com.github.hemoptysisheart.ui.state.InteractionImpact
import com.github.hemoptysisheart.ui.state.scaffold.TopBarState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * [androidx.lifecycle.ViewModel]에 일부 기능을 추가한 클래스.
 *
 * @param topBar `@Composable Scaffold`의 상단 바 초기값.
 */
open class ScaffoldContentViewModel<TB : TopBarState>(
    /**
     * 로그에 사용할 태그. 추천값은 클래스 이름.
     */
    protected val tag: String,
    /**
     * [androidx.lifecycle.viewModelScope]에서 발생한 예외를 마지막으로 처리하는 핸들러.
     */
    @Suppress("MemberVisibilityCanBePrivate")
    protected val fallbackCoroutineExceptionHandler: CoroutineExceptionHandler = object : CoroutineExceptionHandler {
        override val key = CoroutineExceptionHandler.Key

        override fun handleException(context: CoroutineContext, exception: Throwable) {
            Log.w(tag, "#handleException : ${exception.message}", exception)
        }
    },
    /**
     * `@Composable Scaffold`의 상단 바 초기값. 앱 사용 중에 상단바 상태가 바껴야 한다면 구현 클래스에서 [scaffoldPump]를 이용하여 변경해야 한다.
     *
     * @see scaffoldPump
     */
    topBar: TB? = null
) : androidx.lifecycle.ViewModel(), DefaultLifecycleObserver {
    /**
     * 사용자 인터랙션을 막지 않지만 사용자에게 처리중임을 표시해야 하는 코루틴의 수.
     */
    private val visibleImpacts = AtomicInteger(0)

    /**
     * 사용자 인터랙션을 막으면서 사용자에게 처리중임을 표시해야 하는 코루틴의 수.
     */
    private val blockingImpacts = AtomicInteger(0)

    private val _visibleProgress = MutableStateFlow(false)

    /**
     * 사용자 인터랙션을 막지는 않지만 처리중임을 표시해야 하는지 여부.
     */
    val visibleProgress: StateFlow<Boolean> = _visibleProgress

    private val _blockingProgress = MutableStateFlow(false)

    /**
     * 사용자 인터랙션을 막는 처리중임을 표시해야 하는지 여부.
     */
    val blockingProgress: StateFlow<Boolean> = _blockingProgress

    @Inject
    lateinit var scaffoldPump: ScaffoldPump

    init {
        Log.d(tag, "#init called.")

        scaffoldPump.update(topBar)
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
     * @return [kotlinx.coroutines.Job].
     */
    protected fun launch(
        impact: InteractionImpact = InteractionImpact.NONE,
        exceptionHandler: (Exception) -> Unit = { e ->
            Log.w(tag, "#launch.exceptionHandler : ${e.message}", e)
            throw e
        },
        context: CoroutineContext = EmptyCoroutineContext + fallbackCoroutineExceptionHandler,
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
            Log.w(tag, "#async.exceptionHandler : ${e.message}", e)
            throw e
        },
        context: CoroutineContext = EmptyCoroutineContext + fallbackCoroutineExceptionHandler,
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

    /**
     * 공통 처리를 하기 위해 [onCreate]를 `final`로 선언하고, 구현 클래스 내부에서 처리할 내용을 위해 마련한 빈 메서드.
     */
    protected open fun doOnCreate(owner: LifecycleOwner) {}

    /**
     * 공통 처리를 하기 위해 [onStart]를 `final`로 선언하고, 구현 클래스 내부에서 처리할 내용을 위해 마련한 빈 메서드.
     */
    protected open fun doOnStart(owner: LifecycleOwner) {}

    /**
     * 공통 처리를 하기 위해 [onResume]를 `final`로 선언하고, 구현 클래스 내부에서 처리할 내용을 위해 마련한 빈 메서드.
     */
    protected open fun doOnResume(owner: LifecycleOwner) {}

    /**
     * 공통 처리를 하기 위해 [onPause]를 `final`로 선언하고, 구현 클래스 내부에서 처리할 내용을 위해 마련한 빈 메서드.
     */
    protected open fun doOnPause(owner: LifecycleOwner) {}

    /**
     * 공통 처리를 하기 위해 [onStop]를 `final`로 선언하고, 구현 클래스 내부에서 처리할 내용을 위해 마련한 빈 메서드.
     */
    protected open fun doOnStop(owner: LifecycleOwner) {}

    /**
     * 공통 처리를 하기 위해 [onDestroy]를 `final`로 선언하고, 구현 클래스 내부에서 처리할 내용을 위해 마련한 빈 메서드.
     */
    protected open fun doOnDestroy(owner: LifecycleOwner) {}

    /**
     * 공통 처리를 하기 위해 [onCleared]를 `final`로 선언하고, 구현 클래스 내부에서 처리할 내용을 위해 마련한 빈 메서드.
     */
    protected open fun doOnCleared() {}

    /**
     * **주의 : ViewModel 인스턴스의 수명주기가 아니라 UI(화면 단위)의 수명주기이기 때문에, 다른 화면으로 이동했다가 돌아올 때도 실행된다.**
     *
     * @see doOnCreate
     */
    final override fun onCreate(owner: LifecycleOwner) {
        Log.d(tag, "#onCreate args : owner=$owner")
        super.onCreate(owner)

        doOnCreate(owner)
    }

    /**
     * **주의 : ViewModel 인스턴스의 수명주기가 아니라 UI(화면 단위)의 수명주기이기 때문에, 다른 화면으로 이동했다가 돌아올 때도 실행된다.**
     *
     * @see doOnStart
     */
    final override fun onStart(owner: LifecycleOwner) {
        Log.d(tag, "#onStart : owner=$owner")
        super.onStart(owner)

        doOnStart(owner)
    }

    /**
     * @see doOnResume
     */
    final override fun onResume(owner: LifecycleOwner) {
        Log.d(tag, "#onResume : owner=$owner")
        super.onResume(owner)

        doOnResume(owner)
    }

    /**
     * @see doOnPause
     */
    final override fun onPause(owner: LifecycleOwner) {
        Log.d(tag, "#onPause args : owner=$owner")
        super.onPause(owner)

        doOnPause(owner)
    }

    /**
     * **주의 : ViewModel 인스턴스의 수명주기가 아니라 UI(화면 단위)의 수명주기이기 때문에, 다른 화면으로 이동할 때도 실행된다.**
     *
     * @see doOnStop
     */
    final override fun onStop(owner: LifecycleOwner) {
        Log.d(tag, "#onStop args : owner=$owner")
        super.onStop(owner)

        doOnStop(owner)
    }

    /**
     * @see doOnDestroy
     */
    final override fun onDestroy(owner: LifecycleOwner) {
        Log.d(tag, "#onDestroy args : owner=$owner")
        super.onDestroy(owner)

        doOnDestroy(owner)
    }

    /**
     * @see doOnCleared
     */
    final override fun onCleared() {
        Log.d(tag, "#onCleared called.")
        super.onCleared()

        doOnCleared()
    }

    override fun toString() = listOf(
        "fallbackCoroutineExceptionHandler=$fallbackCoroutineExceptionHandler",
        "visibleImpacts=$visibleImpacts",
        "blockingImpacts=$blockingImpacts",
        "visibleProgress=${visibleProgress.value}",
        "blockingProgress=${blockingProgress.value}",
        "scaffoldPump=$scaffoldPump"
    ).joinToString(", ")
}

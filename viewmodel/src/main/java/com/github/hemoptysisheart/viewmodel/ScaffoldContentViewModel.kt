package com.github.hemoptysisheart.viewmodel

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.github.hemoptysisheart.statepump.ScaffoldPump
import com.github.hemoptysisheart.ui.state.scaffold.TopBarState
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * [androidx.lifecycle.ViewModel]에 일부 기능을 추가한 클래스.
 */
abstract class ScaffoldContentViewModel<TB : TopBarState>(
    /**
     * 로그에 사용할 태그. 추천값은 클래스 이름.
     */
    tag: String,
    /**
     * [androidx.lifecycle.viewModelScope]에서 발생한 예외를 마지막으로 처리하는 핸들러.
     */
    fallbackCoroutineExceptionHandler: CoroutineExceptionHandler = object : CoroutineExceptionHandler {
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
    private val topBar: TB? = null
) : BaseViewModel(tag, fallbackCoroutineExceptionHandler), DefaultLifecycleObserver {
    @Inject
    lateinit var scaffoldPump: ScaffoldPump

    init {
        Log.d(tag, "#init called.")
    }

    override fun doOnCreate(owner: LifecycleOwner) {
        super.doOnCreate(owner)
        scaffoldPump.update(topBar)
    }

    override fun toString() = listOf(
        "scaffoldPump=$scaffoldPump"
    ).joinToString(", ", "${super.toString()}, ")
}

package com.github.hemoptysisheart.sample.model

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

/**
 * 애플리케이션과 동일한 라이프사이클을 가지는 [CoroutineScope].
 */
class ApplicationCoroutineScope(
    private val scope: CoroutineScope = CoroutineScope(SupervisorJob())
) : CoroutineScope by scope {
    override fun toString() = listOf(
        "scope=$scope"
    ).joinToString(", ", "ApplicationCoroutineScope(", ")")
}

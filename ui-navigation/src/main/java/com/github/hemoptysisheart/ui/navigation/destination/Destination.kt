package com.github.hemoptysisheart.ui.navigation.destination

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink

/**
 * 백스택 엔트리 아이템을 추상화 하는 인터페이스.
 */
interface Destination {
    /**
     * 백스택 엔트리 아이템의 고유 식별자. 내비게이션 그래프 등록시 ID로 사용한다.
     */
    val id: String

    val arguments: List<NamedNavArgument>

    val deepLinks: List<NavDeepLink>

    /**
     * 백스택 엔트리 아이템의 경로를 생성한다.
     */
    fun route(vararg arguments: Any): String
}

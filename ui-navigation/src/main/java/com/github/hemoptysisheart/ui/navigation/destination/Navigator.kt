package com.github.hemoptysisheart.ui.navigation.destination

/**
 * 화면 사이의 이동(백스택의 변경)을 추상화 하는 인터페이스.
 *
 * 각 화면에 대응하는 [Destination]을 구현하고, [Navigator]를 통해 이동한다.
 * 구현 클래스는 이 화면에서 다른 화면으로 이동하는 방법을 제공해야 한다.
 */
interface Navigator {
    /**
     * 기준이 되는 화면. 이 [Navigator]가 의미하는 화면이다.
     * 이 화면으로 이동하려면 [Destination.route]를 사용한다.
     */
    val destination: Destination

    /**
     * 이전 화면으로 이동한다.
     */
    fun back()

    /**
     * 가상 키보드를 보이거나 숨긴다.
     *
     * @param show `true`이면 키보드를 보이고, `false`이면 숨긴다.
     */
    fun keyboard(show: Boolean)
}
package com.github.hemoptysisheart.ui.state

/**
 * 어떤 작업이 유저 인터렉션에 미치는 영향.
 */
enum class InteractionImpact {
    /**
     * 작업이 사용자 인터랙션에 영향을 미치지 않는 경우.
     */
    NONE,

    /**
     * 작업이 실행 중이지만 사용자 인터랙션에 영향을 미치지 않는 경우.
     * 그러나 사용자에게 작업이 실행 중임을 알려야 하는 경우.
     */
    VISIBLE,

    /**
     * 작업이 사용자 인터랙션을 차단해야 하는 경우.
     */
    BLOCKING
}

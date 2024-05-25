package com.github.hemoptysisheart.viewmodel

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.test.isRootTest
import io.mockk.mockk
import kotlinx.coroutines.CoroutineExceptionHandler
import mu.KotlinLogging

class BaseViewModelTest : BehaviorSpec() {
    private val logger = KotlinLogging.logger { }

    private lateinit var viewModel: BaseViewModel

    private fun viewModel(
        tag: String = "BaseViewModelTest",
        fallbackCoroutineExceptionHandler: CoroutineExceptionHandler = mockk()
    ): BaseViewModel = object : BaseViewModel(tag, fallbackCoroutineExceptionHandler) {
    }

    init {
        beforeTest {
            if (it.isRootTest()) {
                viewModel = viewModel()
            }
        }

        given("gradle 설정용 확인 테스트") {
            logger.info("[GIVEN]")
            `when`("when") {
                logger.info("[WHEN]")
                then("then") {
                    logger.info("[THEN]")
                }
            }
        }
    }
}

package com.github.hemoptysisheart.ui.compose

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.hemoptysisheart.ui.state.SimpleTextFieldState
import mu.KotlinLogging
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@Suppress("NonAsciiCharacters")
class TextFieldTest {
    private val logger = KotlinLogging.logger { }

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `빈 텍스트 필드 출력`() {
        // GIVEN
        val state = SimpleTextFieldState(text = "", onValueChange = {})
        logger.info("[GIVEN] state=$state")

        // WHEN
        composeTestRule.setContent {
            TextField(state = state)
        }
        composeTestRule.waitForIdle()

        // THEN
        composeTestRule.onNodeWithTag("${state.key}")
            .assertExists()
    }
}

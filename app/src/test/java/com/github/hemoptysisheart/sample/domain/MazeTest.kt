package com.github.hemoptysisheart.sample.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import mu.KotlinLogging

class MazeTest : BehaviorSpec() {
    private val logger = KotlinLogging.logger { }

    init {
        given("new") {
            `when`("3x5") {
                val maze = Maze(3, 5)
                logger.info("[WHEN] maze=$maze")

                then("OK") {
                    maze.width shouldBe 3
                    maze.height shouldBe 5
                }
            }
        }
    }
}

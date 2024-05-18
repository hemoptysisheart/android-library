package com.github.hemoptysisheart.sample.domain

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe
import mu.KotlinLogging
import kotlin.Int.Companion.MAX_VALUE
import kotlin.random.Random.Default.nextInt

class LinkTest : BehaviorSpec() {
    private val logger = KotlinLogging.logger { }

    override fun isolationMode() = IsolationMode.InstancePerLeaf

    init {
        given("opposite - 동서 링크에서 반대쪽 셀 찾기") {
            val west = Cell(0, 0)
            val east = Cell(1, 0)
            val link = Link(west, east)
            logger.info("[GIVEN] west=$west, east=$east, link=$link")

            `when`("반대쪽 셀을 찾으면") {
                val oppositeWest = link.opposite(west)
                val oppositeEast = link.opposite(east)
                logger.info("[WHEN] oppositeWest=$oppositeWest, oppositeEast=$oppositeEast")

                then("반대쪽 셀을 찾을 수 있어야 한다") {
                    oppositeWest shouldBe east
                    oppositeEast shouldBe west
                }
            }
        }

        given("opposite - 남북 링크에서 반대쪽 셀 찾기") {
            val north = Cell(0, 0)
            val south = Cell(0, 1)
            val link = Link(north, south)
            logger.info("[GIVEN] north=$north, south=$south, link=$link")

            `when`("반대쪽 셀을 찾으면") {
                val oppositeNorth = link.opposite(north)
                val oppositeSouth = link.opposite(south)
                logger.info("[WHEN] oppositeNorth=$oppositeNorth, oppositeSouth=$oppositeSouth")

                then("반대쪽 셀을 찾을 수 있어야 한다") {
                    oppositeNorth shouldBe south
                    oppositeSouth shouldBe north
                }
            }
        }

        table(
            headers("x", "y"),
            row(nextInt(MAX_VALUE), 0),
            row(nextInt(MAX_VALUE), 1),
            row(nextInt(MAX_VALUE), 2),
            row(nextInt(MAX_VALUE), nextInt(MAX_VALUE))
        ).forAll { x, y ->
            given("opposite - 임의의 동서 링크에서 반대쪽 셀 찾기") {
                val west = Cell(x, y)
                val east = Cell(x + 1, y)
                val link = Link(west, east)
                logger.info("[GIVEN] a=$west, b=$east, link=$link")

                `when`("반대쪽 셀을 찾으면") {
                    val oppositeWest = link.opposite(west)
                    val oppositeEast = link.opposite(east)
                    logger.info("[WHEN] oppositeWest=$oppositeWest, oppositeEast=$oppositeEast")

                    then("반대쪽 셀을 찾을 수 있어야 한다") {
                        oppositeWest shouldBe east
                        oppositeEast shouldBe west
                    }
                }
            }
        }

        table(
            headers("x", "y"),
            row(0, nextInt(MAX_VALUE)),
            row(1, nextInt(MAX_VALUE)),
            row(2, nextInt(MAX_VALUE)),
            row(nextInt(MAX_VALUE), nextInt(MAX_VALUE))
        ).forAll { x, y ->
            given("opposite - 임의의 남북 링크에서 반대쪽 셀 찾기") {
                val north = Cell(x, y)
                val south = Cell(x, y + 1)
                val link = Link(north, south)
                logger.info("[GIVEN] a=$north, b=$south, link=$link")

                `when`("반대쪽 셀을 찾으면") {
                    val oppositeNorth = link.opposite(north)
                    val oppositeSouth = link.opposite(south)
                    logger.info("[WHEN] oppositeNorth=$oppositeNorth, oppositeSouth=$oppositeSouth")

                    then("반대쪽 셀을 찾을 수 있어야 한다") {
                        oppositeNorth shouldBe south
                        oppositeSouth shouldBe north
                    }
                }
            }
        }

        given("opposite - 동서 링크에서 링크에 없는 셀의 반대쪽 셀 찾기") {
            val west = Cell(0, 0)
            val east = Cell(1, 0)
            val link = Link(west, east)
            val other = Cell(4, 4)
            logger.info("[GIVEN] west=$west, east=$east, link=$link, other=$other")

            `when`("링크에 없는 셀의 반대쪽 셀을 찾으면") {
                val opposite = link.opposite(other)
                logger.info("[WHEN] opposite=$opposite")

                then("반대쪽 셀을 찾을 수 없어야 한다") {
                    opposite shouldBe null
                }
            }
        }

        given("opposite - 남북 링크에서 링크에 없는 셀의 반대쪽 셀 찾기") {
            val north = Cell(0, 0)
            val south = Cell(0, 1)
            val link = Link(north, south)
            val other = Cell(4, 4)
            logger.info("[GIVEN] north=$north, south=$south, link=$link, other=$other")

            `when`("링크에 없는 셀의 반대쪽 셀을 찾으면") {
                val opposite = link.opposite(other)
                logger.info("[WHEN] opposite=$opposite")

                then("반대쪽 셀을 찾을 수 없어야 한다") {
                    opposite shouldBe null
                }
            }
        }

        table(
            headers("x", "y", "other"),
            row(nextInt(MAX_VALUE / 2), 0, Cell(nextInt(MAX_VALUE / 2, MAX_VALUE), 0)),
            row(nextInt(MAX_VALUE / 2), 1, Cell(nextInt(MAX_VALUE / 2, MAX_VALUE), 1)),
            row(nextInt(MAX_VALUE / 2), 2, Cell(nextInt(MAX_VALUE / 2, MAX_VALUE), 2)),
            row(
                nextInt(MAX_VALUE / 2),
                nextInt(MAX_VALUE / 2),
                Cell(nextInt(MAX_VALUE / 2, MAX_VALUE), nextInt(MAX_VALUE / 2, MAX_VALUE))
            )
        ).forAll { x, y, other ->
            given("opposite - 임의의 동서 링크에서 링크에 없는 셀의 반대쪽 셀 찾기") {
                val west = Cell(x, y)
                val east = Cell(x + 1, y)
                val link = Link(west, east)
                logger.info("[GIVEN] a=$west, b=$east, link=$link, other=$other")

                `when`("링크에 없는 셀의 반대쪽 셀을 찾으면") {
                    val opposite = link.opposite(other)
                    logger.info("[WHEN] opposite=$opposite")

                    then("반대쪽 셀을 찾을 수 없어야 한다") {
                        opposite shouldBe null
                    }
                }
            }
        }

        table(
            headers("x", "y", "other"),
            row(0, nextInt(MAX_VALUE / 2), Cell(0, nextInt(MAX_VALUE / 2, MAX_VALUE))),
            row(1, nextInt(MAX_VALUE / 2), Cell(1, nextInt(MAX_VALUE / 2, MAX_VALUE))),
            row(2, nextInt(MAX_VALUE / 2), Cell(2, nextInt(MAX_VALUE / 2, MAX_VALUE))),
            row(
                nextInt(MAX_VALUE / 2),
                nextInt(MAX_VALUE / 2),
                Cell(nextInt(MAX_VALUE / 2, MAX_VALUE), nextInt(MAX_VALUE / 2, MAX_VALUE))
            )
        ).forAll { x, y, other ->
            given("opposite - 임의의 남북 링크에서 링크에 없는 셀의 반대쪽 셀 찾기") {
                val north = Cell(x, y)
                val south = Cell(x, y + 1)
                val link = Link(north, south)
                logger.info("[GIVEN] a=$north, b=$south, link=$link, other=$other")

                `when`("링크에 없는 셀의 반대쪽 셀을 찾으면") {
                    val opposite = link.opposite(other)
                    logger.info("[WHEN] opposite=$opposite")

                    then("반대쪽 셀을 찾을 수 없어야 한다") {
                        opposite shouldBe null
                    }
                }
            }
        }
    }
}

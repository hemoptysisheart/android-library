package com.github.hemoptysisheart.sample.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.test.isRootTest
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe
import mu.KotlinLogging
import kotlin.Int.Companion.MAX_VALUE
import kotlin.random.Random.Default.nextInt

class CellTest : BehaviorSpec() {
    private val logger = KotlinLogging.logger { }

    private var low = Cell(nextInt(MAX_VALUE / 2), nextInt(MAX_VALUE / 2))

    /**
     * 임의의 높은 좌표값을 가지는 셀.
     */
    private var high = Cell(nextInt(MAX_VALUE / 2, MAX_VALUE), nextInt(MAX_VALUE / 2, MAX_VALUE))

    init {
        beforeTest {
            if (it.isRootTest()) {
                do {
                    low = Cell(nextInt(MAX_VALUE / 2), nextInt(MAX_VALUE / 2))
                    high = Cell(nextInt(MAX_VALUE / 2, MAX_VALUE), nextInt(MAX_VALUE / 2, MAX_VALUE))
                } while (low == high)
                logger.info("[SETUP] low=$low, high=$high")
            }
        }

        given("direction - 동서 링크의 반대쪽 셀의 방향 찾기.") {
            val west = Cell(0, 0)
            val east = Cell(1, 0)
            val link = Link(west, east)
            logger.info("[GIVEN] west=$west, east=$east, link=$link")

            `when`("동서 링크의 반대쪽 셀의 방향을 찾으면") {
                val directionOppositeWest = west.direction(link)
                val directionOppositeEast = east.direction(link)
                logger.info("[WHEN] directionOppositeWest=$directionOppositeWest, directionOppositeEast=$directionOppositeEast")

                then("방향을 반환한다.") {
                    directionOppositeWest shouldBe Direction.EAST
                    directionOppositeEast shouldBe Direction.WEST
                }
            }
        }

        given("direction - 남북 링크의 반대쪽 셀의 방향 찾기.") {
            val north = Cell(0, 0)
            val south = Cell(0, 1)
            val link = Link(north, south)
            logger.info("[GIVEN] north=$north, south=$south, link=$link")

            `when`("남북 링크의 반대쪽 셀의 방향을 찾으면") {
                val directionOppositeNorth = north.direction(link)
                val directionOppositeSouth = south.direction(link)
                logger.info("[WHEN] directionOppositeNorth=$directionOppositeNorth, directionOppositeSouth=$directionOppositeSouth")

                then("방향을 반환한다.") {
                    directionOppositeNorth shouldBe Direction.SOUTH
                    directionOppositeSouth shouldBe Direction.NORTH
                }
            }
        }

        table(
            headers("x", "y"),
            row(nextInt(MAX_VALUE), 0),
            row(nextInt(MAX_VALUE), 1),
            row(nextInt(MAX_VALUE), 2),
            row(nextInt(MAX_VALUE), nextInt(MAX_VALUE)),
        ).forAll { x, y ->
            given("direction - 임의의 동서 링크에서 반대쪽 셀의 방향 찾기.") {
                val west = Cell(x, y)
                val east = Cell(x + 1, y)
                val link = Link(west, east)
                logger.info("[GIVEN] west=$west, east=$east, link=$link")

                `when`("동서 링크의 반대쪽 셀의 방향을 찾으면") {
                    val directionOppositeWest = west.direction(link)
                    val directionOppositeEast = east.direction(link)
                    logger.info("[WHEN] directionOppositeWest=$directionOppositeWest, directionOppositeEast=$directionOppositeEast")

                    then("방향을 반환한다.") {
                        directionOppositeWest shouldBe Direction.EAST
                        directionOppositeEast shouldBe Direction.WEST
                    }
                }
            }
        }

        table(
            headers("x", "y"),
            row(0, nextInt(MAX_VALUE)),
            row(1, nextInt(MAX_VALUE)),
            row(2, nextInt(MAX_VALUE)),
            row(nextInt(MAX_VALUE), nextInt(MAX_VALUE)),
        ).forAll { x, y ->
            given("direction - 임의의 남북 링크에서 반대쪽 셀의 방향 찾기.") {
                val north = Cell(x, y)
                val south = Cell(x, y + 1)
                val link = Link(north, south)
                logger.info("[GIVEN] north=$north, south=$south, link=$link")

                `when`("남북 링크의 반대쪽 셀의 방향을 찾으면") {
                    val directionOppositeNorth = north.direction(link)
                    val directionOppositeSouth = south.direction(link)
                    logger.info("[WHEN] directionOppositeNorth=$directionOppositeNorth, directionOppositeSouth=$directionOppositeSouth")

                    then("방향을 반환한다.") {
                        directionOppositeNorth shouldBe Direction.SOUTH
                        directionOppositeSouth shouldBe Direction.NORTH
                    }
                }
            }
        }

        table(
            headers("x", "y", "link"),
            row(0, 0, Link(high, Cell(high.x + 1, high.y))),
            row(0, 0, Link(high, Cell(high.x, high.y + 1))),
            row(1, 1, Link(high, Cell(high.x + 1, high.y))),
            row(1, 1, Link(high, Cell(high.x, high.y + 1))),
            row(low.x, low.y, Link(high, Cell(high.x + 1, high.y))),
            row(low.x, low.y, Link(high, Cell(high.x, high.y + 1))),
        ).forAll { x, y, link ->
            given("direction - 링크에 포함되지 않은 셀의 방향 찾기.") {
                val cell = Cell(x, y)
                logger.info("[GIVEN] cell=$cell, link=$link")

                `when`("링크에 포함되지 않은 셀의 방향을 찾으면") {
                    val direction = cell.direction(link)
                    logger.info("[WHEN] direction=$direction")

                    then("null을 반환한다.") {
                        direction shouldBe null
                    }
                }
            }
        }
    }
}

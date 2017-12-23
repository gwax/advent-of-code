package com.gwax.aoc2017.day20

import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ParticleSwarmTest {
    @Test
    fun vectorFromStringTest() {
        assertEquals(Vector(-1, 2, -3), Vector.fromString("<-1,2,-3>"))
    }

    @Test
    fun pointFromStringTest() {
        assertEquals(
                Point(
                        Vector(1, 2, 3),
                        Vector(4, 5, 6),
                        Vector(7, 8, 9)),
                Point.fromString("p=<1,2,3>, v=<4,5,6>, a=<7,8,9>")
        )
    }

    @Test
    fun pointIntersectsTest() {
        assertTrue {
            Point(
                    Vector(1, 1, 1),
                    Vector(2, 2, 2),
                    Vector(3, 3, 3))
                    .intersects(
                            Point(
                                    Vector(33, 33, 33),
                                    Vector(-2, -2, -2),
                                    Vector(-3, -3, -3)),
                            BigInteger.valueOf(2))
        }
        assertFalse {
            Point(
                    Vector(1, 1, 2),
                    Vector(2, 2, 2),
                    Vector(3, 3, 3))
                    .intersects(
                            Point(
                                    Vector(33, 33, 33),
                                    Vector(-2, -2, -2),
                                    Vector(-3, -3, -3)),
                            BigInteger.valueOf(2))
        }
    }

    @Test
    fun pointCollisionTimeTest() {
        assertEquals(
                BigInteger.valueOf(2),
                Point(
                        Vector(1, 1, 1),
                        Vector(2, 2, 2),
                        Vector(3, 3, 3))
                        .collisionTime(
                                Point(
                                        Vector(33, 33, 33),
                                        Vector(-2, -2, -2),
                                        Vector(-3, -3, -3))))
        assertEquals(
                null,
                Point(
                        Vector(1, 1, 2),
                        Vector(2, 2, 2),
                        Vector(3, 3, 3))
                        .collisionTime(
                                Point(
                                        Vector(33, 33, 33),
                                        Vector(-2, -2, -2),
                                        Vector(-3, -3, -3))))
    }

    @Test
    fun allCollisionTimesTest() {
        val one = Point(
                Vector(1, 1, 1),
                Vector(2, 2, 2),
                Vector(3, 3, 3))
        val two = Point(
                Vector(1, 1, 2),
                Vector(2, 2, 2),
                Vector(3, 3, 3))
        val three = Point(
                Vector(33, 33, 33),
                Vector(-2, -2, -2),
                Vector(-3, -3, -3))
        assertEquals(
                mapOf(BigInteger.valueOf(2L) to listOf(Pair(one, three))),
                allCollisionTimes(listOf(one, two, three)))
    }

    @Test
    fun removeCollisionsTest() {
        val one = Point(
                Vector(1, 1, 1),
                Vector(2, 2, 2),
                Vector(3, 3, 3))
        val two = Point(
                Vector(1, 1, 2),
                Vector(2, 2, 2),
                Vector(3, 3, 3))
        val three = Point(
                Vector(33, 33, 33),
                Vector(-2, -2, -2),
                Vector(-3, -3, -3))
        assertEquals(
                listOf(two),
                removeCollisions(listOf(one, two, three)))
    }

    @Test
    fun longQuadraticsTest() {
        val abcSolutions: List<Pair<Triple<Long, Long, Long>, List<BigInteger>>> = listOf(
                Pair(Triple(1L, 2L, 1L), listOf(BigInteger.valueOf(-1L))),
                Pair(Triple(2L, 1L, 2L), listOf()),
                Pair(Triple(2L, -2L, -24L), listOf(BigInteger.valueOf(-3L), BigInteger.valueOf(4L)))
        )
        abcSolutions.forEach { (abc, solution) ->
            val (a, b, c) = abc
            assertEquals(
                    solution.sorted(),
                    bigIntegerQuadratics(BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c)).sorted())
        }
    }

}
package com.gwax.aoc2017.day20

import org.junit.Test
import kotlin.test.assertEquals

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
    fun tickTest() {
        val p = Point(Vector(1,2,3), Vector(10, -20, 30), Vector(100, 200, 300))
        assertEquals(
                Point(Vector(11, -18, 33), Vector(110, 180, 330), Vector(100, 200, 300)),
                p.tick())
    }

    @Test
    fun removeCollisionsTest() {
        assertEquals(
                listOf(
                        Point(Vector(3, 2, 1),
                                Vector(4, 5, 6),
                                Vector(7, 8, 9))),
                removeCollisions(listOf(
                        Point(Vector(1, 2, 3),
                                Vector(4, 5, 6),
                                Vector(7, 8, 9)),
                        Point(Vector(1, 2, 3),
                                Vector(6, 5, 4),
                                Vector(9, 8, 7)),
                        Point(Vector(3, 2, 1),
                                Vector(4, 5, 6),
                                Vector(7, 8, 9)))))
    }

    @Test
    fun stepTest() {
        val input = """
            p=<-6,0,0>, v=< 3,0,0>, a=< 0,0,0>
            p=<-4,0,0>, v=< 2,0,0>, a=< 0,0,0>
            p=<-2,0,0>, v=< 1,0,0>, a=< 0,0,0>
            p=< 3,0,0>, v=<-1,0,0>, a=< 0,0,0>
            """.trimIndent()
        var points = input.lines().map { Point.fromString(it) }
        assertEquals(4, points.size)
        points = step(points)
        assertEquals(4, points.size)
        points = step(points)
        assertEquals(4, points.size)
        points = step(points)
        assertEquals(1, points.size)
    }

    @Test
    fun runUntilCollide() {
        val input = """
            p=<-6,0,0>, v=< 3,0,0>, a=< 0,0,0>
            p=<-4,0,0>, v=< 2,0,0>, a=< 0,0,0>
            p=<-2,0,0>, v=< 1,0,0>, a=< 0,0,0>
            p=< 3,0,0>, v=<-1,0,0>, a=< 0,0,0>
            """.trimIndent()
        var points = input.lines().map { Point.fromString(it) }
        assertEquals(4, points.size)
        points = removeCollisions(points.map { it.tick() })
        assertEquals(4, points.size)
        points = removeCollisions(points.map { it.tick() })
        assertEquals(1, points.size)
    }
}
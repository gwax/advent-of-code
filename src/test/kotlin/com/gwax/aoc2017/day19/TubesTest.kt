package com.gwax.aoc2017.day19

import org.junit.Test
import kotlin.test.assertEquals

val testInput = """
     |
     |  +--+
     A  |  C
 F---|----E|--+
     |  |  |  D
     +B-+  +--+
""".trimIndent()

class TubesTest {
    @Test
    fun buildRouteTest() {
        val route = buildRoute(testInput)
        assertEquals(Letter('F'), route.get(Point(0, 3)))
        assertEquals(Vertical, route.get(Point(4, 0)))
        assertEquals(6, route.size)
        assertEquals(listOf(14), route.map { it.size }.distinct())
    }

    @Test
    fun startPacketTest() {
        val route = buildRoute(testInput)
        assertEquals(
                Packet(Point(4, 0), Direction.S, mutableListOf(), mutableListOf(Direction.S)),
                startPacket(route))
    }

    @Test
    fun runStepPacketTest() {
        val route = buildRoute(testInput)
        val packet = startPacket(route)
        do {
            val status = packet.step(route)
        } while (status == Status.Active)
        assertEquals("ABCDEF", packet.seen.map { it.char }.joinToString(""))
        assertEquals(
                // 6 steps down (including the first line at the top of the diagram).
                List(6, { Direction.S }) +
                // 3 steps right.
                        List(3, { Direction.E }) +
                // 4 steps up.
                        List(4, { Direction.N }) +
                // 3 steps right.
                        List(3, { Direction.E }) +
                // 4 steps down.
                        List(4, { Direction.S }) +
                // 3 steps right.
                        List(3, { Direction.E }) +
                // 2 steps up.
                        List(2, { Direction.N }) +
                // 13 steps left (including the F it stops on).
                        List(13, { Direction.W }),
                packet.steps.toList())
        assertEquals(38, packet.steps.size)
    }
}
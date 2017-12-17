package com.gwax.aoc2017.day3

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.test.assertEquals

@RunWith(Parameterized::class)
class NextPointTest(private val start: Point, private val end: Point) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{0}->{1}")
        fun data(): Collection<Array<Point>> {
            return listOf(
                    arrayOf(Point(0, 0), Point( 1, 0)),
                    arrayOf(Point(1, 0), Point( 1, 1)),
                    arrayOf(Point(1, 1), Point( 0, 1)),
                    arrayOf(Point(0, 1), Point(-1, 1)),
                    arrayOf(Point(-1, 1), Point(-1, 0)),
                    arrayOf(Point(-1, 0), Point(-1, -1)),
                    arrayOf(Point(-1, -1), Point( 0, -1)),
                    arrayOf(Point(0, -1), Point( 1, -1)),
                    arrayOf(Point(1, -1), Point( 2, -1)),
                    arrayOf(Point(2, -1), Point( 2, 0))
            )
        }
    }

    @Test
    fun nextPoint() {
        assertEquals(end, start.next())
    }
}

class SpiralOutTest {
    @Test
    fun oneStep() {
        assertEquals(Point(0, 0), spiralOut(1))
    }

    @Test
    fun tenSteps() {
        assertEquals(Point(2, -1), spiralOut(10))
    }
}

class SpiralMemoryTest {
    @Test
    fun exampleFour() {
        assertEquals(31, spiralOut(1024).manhattan())
    }
}

class PartTwoTest {
    @Test
    fun testStuff() {
        assertEquals(2, partTwo(1))
        assertEquals(4, partTwo(2))
        assertEquals(57, partTwo(54))
    }
}
package com.gwax.aoc2017.day11

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.test.assertEquals

class HexEdTest {
    @Test
    fun testCartesianSteps() {
        assertEquals(
                Point(3, 3),
                listOf(Step.NE, Step.NE, Step.NE).map { it.dPoint }.sum())
    }

    @Test
    fun testStepFactory() {
        assertEquals(
                listOf(Step.N, Step.NE, Step.SE, Step.S, Step.SW, Step.NW),
                Step.fromString("n,ne,se,s,sw,nw"))
    }
}

@RunWith(Parameterized::class)
class ToStepsTest(private val point: Point, private val steps: List<Step>) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{0}")
        fun data(): Collection<Array<out Any>> {
            return listOf(
                    arrayOf(Point(0, 0), listOf<Step>()),
                    arrayOf(Point(1, 1), listOf(Step.NE)),
                    arrayOf(Point(-1, -1), listOf(Step.SW)),
                    arrayOf(Point(1, 0), listOf(Step.SE)),
                    arrayOf(Point(-1, 0), listOf(Step.NW)),
                    arrayOf(Point(0, 1), listOf(Step.N)),
                    arrayOf(Point(0, -1), listOf(Step.S)),
                    arrayOf(Point(3, 5),
                            listOf(Step.NE, Step.NE, Step.NE, Step.N, Step.N)),
                    arrayOf(Point(-2, 2),
                            listOf(Step.NW, Step.NW, Step.N, Step.N))
            )
        }
    }

    @Test
    fun toStepsTest() {
        assertEquals(steps.sorted(), point.toSteps().sorted())
    }
}
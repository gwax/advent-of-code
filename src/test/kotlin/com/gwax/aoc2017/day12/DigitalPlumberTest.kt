package com.gwax.aoc2017.day12

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.test.assertEquals

@RunWith(Parameterized::class)
class MergeSetsTest(private val start: List<Set<Int>>, private val end: List<Set<Int>>) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<out Any?>> = listOf(
                arrayOf(listOf<Set<Int>>(), listOf<Set<Int>>()),
                arrayOf(listOf(setOf(0,1), setOf(1,2), setOf(3,4)),
                        listOf(setOf(0,1,2), setOf(3,4)))
        )
    }

    @Test
    fun mergeSetsTest() {
        assertEquals(end, mergeSets(start))
    }
}

class DigitalPlumberTest {
    @Test
    fun parsePipesTest() {
        val input = """
            0 <-> 2
            1 <-> 1
            2 <-> 0, 3, 4
            3 <-> 2, 4
            4 <-> 2, 3, 6
            5 <-> 6
            6 <-> 4, 5
            """.trimIndent()
        val expected = listOf(
                setOf(0,2),
                setOf(1),
                setOf(2,0,3,4),
                setOf(3,2,4),
                setOf(4,2,3,6),
                setOf(5,6),
                setOf(6,4,5)
        )
        assertEquals(expected, parsePipes(input))
    }
}
package com.gwax.aoc2017.day2

import org.junit.Test
import kotlin.test.assertEquals

class ChecksumTest {
    @Test fun stringToIntMatrix() {
        val input = """
            5 1 9 5
            7 5 3
            2 4 6 8
            """.trimIndent()
        val expected = listOf(
                listOf(5, 1, 9, 5),
                listOf(7, 5, 3),
                listOf(2, 4, 6, 8)
        )
        assertEquals(expected, input.toIntMatrix())
    }

    @Test fun listCombinations() {
        assertEquals(
                listOf(Pair(1, 2), Pair(1, 3), Pair(2, 3)),
                listOf(1, 2, 3).combinations()
        )
    }

    @Test fun example1() {
        val input = """
            5 1 9 5
            7 5 3
            2 4 6 8
            """.trimIndent()
        assertEquals(18, checksumOne(input))
    }

    @Test fun example2() {
        val input = """
            5 9 2 8
            9 4 7 3
            3 8 6 5
            """.trimIndent()
        assertEquals(9, checksumTwo(input))
    }
}
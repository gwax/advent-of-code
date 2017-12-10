package com.gwax.aoc2017.day2

import org.junit.Test
import kotlin.test.assertEquals

class ChecksumTest {
    @Test fun example1() {
        val input = """
            5 1 9 5
            7 5 3
            2 4 6 8
            """.trimIndent()
        assertEquals(18, checksum(input))
    }
}
package com.gwax.aoc2017.day13

import org.junit.Test
import kotlin.test.assertEquals

class PacketScannerTest {
    @Test
    fun testBuildFirewall() {
        val input = """
            1: 2
            3: 4
            """.trimIndent()
        assertEquals(mapOf(1 to 2, 3 to 4), buildFirewall(input))
    }

    @Test
    fun testScannerPosition() {
        assertEquals(0, scannerPosition(3, 0))
        assertEquals(1, scannerPosition(3, 1))
        assertEquals(2, scannerPosition(3, 2))
        assertEquals(1, scannerPosition(3, 3))
        assertEquals(0, scannerPosition(3, 4))
    }

    @Test
    fun testHits() {
        val input = """
            0: 3
            1: 2
            4: 4
            6: 4
            """.trimIndent()
        assertEquals(listOf(0, 6), hits(buildFirewall(input), 0))
    }

    @Test
    fun testSeverity() {
        val input = """
            0: 3
            1: 2
            4: 4
            6: 4
            """.trimIndent()
        assertEquals(24, severity(buildFirewall(input), listOf(0, 6)))
    }

    @Test
    fun testFindTime() {
        val input = """
            0: 3
            1: 2
            4: 4
            6: 4
            """.trimIndent()
        assertEquals(10, findTime(buildFirewall(input)))
    }
}
package com.gwax.aoc2017.day15

import org.junit.Test
import kotlin.test.assertEquals

class DuelingGeneratorsTest {
    private val testASeed = 65
    private val testBSeed = 8921

    @Test
    fun generatorATest() {
        val generatorA = Generator(testASeed, aFactor)
        assertEquals(1092455, generatorA.next())
        assertEquals(1181022009,generatorA.next())
        assertEquals(245556042,generatorA.next())
        assertEquals(1744312007,generatorA.next())
        assertEquals(1352636452,generatorA.next())
    }

    @Test
    fun generatorBTest() {
        val generatorB = Generator(testBSeed, bFactor)
        assertEquals(430625591,generatorB.next())
        assertEquals(1233683848,generatorB.next())
        assertEquals(1431495498,generatorB.next())
        assertEquals(137874439,generatorB.next())
        assertEquals(285222916,generatorB.next())
    }

    @Test
    fun judgeStringTest() {

        val input = listOf(
                1092455,   430625591,
                1181022009,  1233683848,
                245556042,  1431495498,
                1744312007,   137874439,
                1352636452,   285222916)
        val expected = listOf(
                "00000000000100001010101101100111",
                "00011001101010101101001100110111",
                "01000110011001001111011100111001",
                "01001001100010001000010110001000",
                "00001110101000101110001101001010",
                "01010101010100101110001101001010",
                "01100111111110000001011011000111",
                "00001000001101111100110000000111",
                "01010000100111111001100000100100",
                "00010001000000000010100000000100")
        assertEquals(
                expected.map { it.takeLast(16) },
                input.map { it.judgeString() })
    }

    @Test
    fun judgeGeneratorsTest() {
        assertEquals(
                588,
                judgeGenerators(
                        Generator(testASeed, aFactor),
                        Generator(testBSeed, bFactor),
                        40_000_000)
        )
    }
}
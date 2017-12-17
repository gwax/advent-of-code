package com.gwax.aoc2017.day14

import org.junit.Test
import kotlin.test.assertEquals

class DiskDefragmentationTest {
    @Test
    fun hexToBitsTest() {
        assertEquals(
                listOf(false, false, false, true),
                hexToBits("1"))
        assertEquals(
                listOf(true, true, true, true),
                hexToBits("f"))
        assertEquals(
                listOf(true, true, true, true, false, false, false, true),
                hexToBits("f1"))
    }

    @Test
    fun hashGridTest() {
        val expectedRepr = """
            ##.#.#..-->
            .#.#.#.#
            ....#.#.
            #.#.##.#
            .##.#...
            ##..#..#
            .#...#..
            ##.#.##.-->
            |      |
            V      V
            """.trimIndent()
        val expected = expectedRepr.lines()
                .map { it.map { it == '#' }.take(8) }
                .take(8)
        assertEquals(
                expected[0],
                listOf(true, true, false, true, false, true, false, false))
        assertEquals(
                expected,
                hashGrid("flqrgnkx").map { it.take(8) }.take(8)
        )
    }

    @Test
    fun regionGridTest() {
        val input = """
            #.#.#
            ###.#
            ...#.
            ##.##
            ..#..
            """.trimIndent()
        val expected = """
            10102
            11102
            00030
            44033
            00500
            """.trimIndent()
        assertEquals(
                expected.lines().map { it.map { it.toString().toInt() } },
                regionGrid(input.lines().map { it.map { it == '#' } })
        )
    }
}
package com.gwax.aoc2017.day10

import org.junit.Test
import kotlin.test.assertEquals

class DataCircleTest {
    @Test
    fun basicFunctionsTest() {
        assertEquals(DataCircle(2), DataCircle(listOf(0, 1)))
        assertEquals("(~0-1~)", DataCircle(2).toString())
        assertEquals(listOf(0, 1), DataCircle(2).elements)
    }

    @Test
    fun rotationTest() {
        val circle = DataCircle(5)
        assertEquals(listOf(0, 1, 2, 3, 4), circle.elements)
        circle.rotate(2)
        assertEquals(listOf(2, 3, 4, 0, 1), circle.elements)
        circle.rotate(-3)
        assertEquals(listOf(4, 0, 1, 2, 3), circle.elements)
    }

    @Test
    fun tieKnotTest() {
        val circle = DataCircle(5)
        assertEquals(listOf(0, 1, 2, 3, 4), circle.elements)
        circle.tieKnot(1, 2)
        assertEquals(listOf(0, 2, 1, 3, 4), circle.elements)
        circle.tieKnot(4, 3)
        assertEquals(listOf(0, 4, 1, 3, 2), circle.elements)
    }

    @Test
    fun hashStepTest() {
        val circle = DataCircle(5)
        val result = circle.hashStep(0, 3, 0)
        assertEquals(listOf(2, 1, 0, 3, 4), circle.elements)
        assertEquals(StepResult(3, 1), result)
    }

    @Test
    fun knotHashTest() {
        val circle = DataCircle(5)
        circle.knotHash(listOf(3, 4, 1, 5))
        assertEquals(listOf(3, 4, 2, 1, 0), circle.elements)
    }

    @Test
    fun asciiLengthsTest() {
        assertEquals(listOf(49, 44, 50, 44, 51, 17, 31, 73, 47, 23), hashLengths("1,2,3"))
    }

    @Test
    fun denseHashTest() {
        val circle = DataCircle(256)
        circle.knotHash(hashLengths(""), times = 64)
        assertEquals("a2582a3a0e66e6e86e3812dcb672a272", denseHash(circle))
    }
}
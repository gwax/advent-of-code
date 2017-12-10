package com.gwax.aoc2017.day6

import org.junit.Test
import kotlin.test.assertEquals

class ReallocationTest {
    @Test fun reallocateTest() {
        assertEquals(listOf(2, 4, 1, 2), reallocate(listOf(0, 2, 7, 0)))
    }

    @Test fun example1() {
        assertEquals(5, reallocsUntilLoop(listOf(0, 2, 7, 0)))
    }

    @Test fun example2() {
        assertEquals(4, reallocCycleLength(listOf(2, 4, 1, 2)))
    }
}
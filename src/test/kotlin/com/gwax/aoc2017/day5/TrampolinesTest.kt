package com.gwax.aoc2017.day5

import org.junit.Test
import kotlin.test.assertEquals

class TrampolinesTest {
    @Test fun example1() {
        assertEquals(5, processJumps(listOf(0, 3, 0, 1, -3)))
    }

    @Test fun example2() {
        assertEquals(10, processJumps2(listOf(0, 3, 0, 1, -3)))
    }
}
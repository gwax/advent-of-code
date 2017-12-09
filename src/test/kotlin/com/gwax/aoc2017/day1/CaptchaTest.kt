package com.gwax.aoc2017.day1

import org.junit.Test
import kotlin.test.assertEquals


class CaptchaTest {
    @Test fun stringRotateTest() {
        assertEquals("bcda", "abcd".rotate(1))
        assertEquals("dabc", "abcd".rotate(-1))
        assertEquals("abcd", "abcd".rotate(0))
    }

    @Test fun example1() {
        assertEquals(3, solveFirst("1122"))
    }

    @Test fun example2() {
        assertEquals(4, solveFirst("1111"))
    }

    @Test fun example3() {
        assertEquals(0, solveFirst("1234"))
    }

    @Test fun example4() {
        assertEquals(9, solveFirst("91212129"))
    }

    @Test fun example5() {
        assertEquals(6, solveSecond("1212"))
    }

    @Test fun example6() {
        assertEquals(0, solveSecond("1221"))
    }

    @Test fun example7() {
        assertEquals(4, solveSecond("123425"))
    }

    @Test fun example8() {
        assertEquals(12, solveSecond("123123"))
    }

    @Test fun example9() {
        assertEquals(4, solveSecond("12131415"))
    }
}
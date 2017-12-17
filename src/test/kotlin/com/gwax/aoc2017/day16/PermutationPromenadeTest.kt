package com.gwax.aoc2017.day16

import org.junit.Test
import kotlin.test.assertEquals

class PermutationPromenadeTest {
    @Test
    fun spinTest() {
        assertEquals("cdeab", Spin(3).perform("abcde"))
    }

    @Test
    fun exchangeTest() {
        assertEquals("adcbe", Exchange(1, 3).perform("abcde"))
    }

    @Test
    fun partnerTest() {
        assertEquals("cbade", Partner('c', 'a').perform("abcde"))
    }

    @Test
    fun readStepTest() {
        assertEquals(Spin(12), readStep("s12"))
        assertEquals(Exchange(3, 14), readStep("x3/14"))
        assertEquals(Partner('a', 'c'), readStep("pa/c"))
    }

    @Test
    fun example1() {
        var input = "abcde"
        val steps = listOf("s1", "x3/4", "pe/b")
        steps.forEach {
            input = readStep(it).perform(input)
        }
        assertEquals("baedc", input)
    }

    @Test
    fun example1b() {
        var input = "abcde"
        val steps = listOf("s1", "x3/4", "pe/b").map { readStep(it) }
        assertEquals("baedc", runProgram(input, steps, 1))
    }

    @Test
    fun example2() {
        var input = "abcde"
        val steps = listOf("s1", "x3/4", "pe/b").map { readStep(it) }
        assertEquals("baedc", runProgram(input, steps, 1))
    }
}
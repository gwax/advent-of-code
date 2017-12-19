package com.gwax.aoc2017.day18

import org.junit.Test
import kotlin.test.assertEquals

class DuetTest {
    val memory = mutableMapOf<Char, Long>()

    @Test
    fun valuesTest() {
        val reg = Register('a')
        val const = Constant(5)
        assertEquals(0, reg.retrieve(memory))
        assertEquals(5, const.retrieve(memory))
        reg.put(memory, 12)
        assertEquals(12, reg.retrieve(memory))
    }

    @Test
    fun parseProgramTest() {
        val input = """
            set a 1
            add a 2
            mul a a
            mod a 5
            snd a
            set a 0
            rcv a
            jgz a -1
            set a 1
            jgz a -2
            """.trimIndent()
        val program = listOf(
                Set(Register('a'), Constant(1)),
                Add(Register('a'), Constant(2)),
                Mul(Register('a'), Register('a')),
                Mod(Register('a'), Constant(5)),
                Snd(Register('a')),
                Set(Register('a'), Constant(0)),
                Rcv(Register('a')),
                Jgz(Register('a'), Constant(-1)),
                Set(Register('a'), Constant(1)),
                Jgz(Register('a'), Constant(-2))
        )
        assertEquals(program, parseProgram(input))
    }

    @Test
    fun processorDualLoopTest() {
        val input = """
            snd 1
            snd 2
            snd p
            rcv a
            rcv b
            rcv c
            rcv d
            """.trimIndent()
            processorDualLoop(parseProgram(input))
    }
}
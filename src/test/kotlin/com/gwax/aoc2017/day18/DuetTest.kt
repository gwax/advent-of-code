package com.gwax.aoc2017.day18

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

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
    fun sndTest() {
        memory['a'] = 1
        val jmp = Snd(Register('a')).perform(memory)
        assertEquals(1, memory[SND_KEY])
        assertEquals(1, jmp)
    }

    @Test
    fun setTest() {
        memory['a'] = 3
        val jmp1 = Set(Register('b'), Register('a')).perform(memory)
        val jmp2 = Set(Register('c'), Constant(2)).perform(memory)
        assertEquals(3, memory['b'])
        assertEquals(2, memory['c'])
        assertEquals(1, jmp1)
        assertEquals(1, jmp2)
    }

    @Test
    fun addTest() {
        memory['d'] = 4
        val jmp = Add(Register('d'), Constant(1)).perform(memory)
        assertEquals(5, memory['d'])
        assertEquals(1, jmp)
    }

    @Test
    fun mulTest() {
        memory['e'] = 3
        val jmp = Mul(Register('e'), Constant(2)).perform(memory)
        assertEquals(6, memory['e'])
        assertEquals(1, jmp)
    }

    @Test
    fun modTest() {
        memory['f'] = 17
        val jmp = Mod(Register('f'), Constant(9)).perform(memory)
        assertEquals(8, memory['f'])
        assertEquals(1, jmp)
    }

    @Test
    fun rcvTest() {
        memory[SND_KEY] = 3
        memory['a'] = 0
        val jmp = Rcv(Register('a')).perform(memory)
        assertEquals(1, jmp)
        memory['a'] = 1
        val recovered = assertFailsWith(RecoverException::class, {
            Rcv(Register('a')).perform(memory)
        })
        assertEquals(3, recovered.snd)
    }

    @Test
    fun jgzTest() {
        memory['a'] = 0
        assertEquals(
                1,
                Jgz(Register('a'), Constant(5)).perform(memory))
        memory['a'] = 1
        assertEquals(
                5,
                Jgz(Register('a'), Constant(5)).perform(memory))
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
    fun executeTest() {
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
        val recover = assertFailsWith(RecoverException::class, {
            execute(program)
        })
        assertEquals(4, recover.snd)
    }
}
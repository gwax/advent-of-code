package com.gwax.aoc2017.day8

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class FromReprTest {
    @Test
    fun testRegisterOperation() {
        assertEquals(RegisterOperation.DEC, RegisterOperation.fromRepr("dec"))
        assertEquals(RegisterOperation.INC, RegisterOperation.fromRepr("inc"))
        assertFailsWith(IllegalArgumentException::class) {
            RegisterOperation.fromRepr("oth")
        }
    }

    @Test
    fun testRegisterComparison() {
        assertEquals(RegisterComparison.EQ, RegisterComparison.fromRepr("=="))
        assertEquals(RegisterComparison.NE, RegisterComparison.fromRepr("!="))
        assertEquals(RegisterComparison.GT, RegisterComparison.fromRepr(">"))
        assertEquals(RegisterComparison.GE, RegisterComparison.fromRepr(">="))
        assertEquals(RegisterComparison.LT, RegisterComparison.fromRepr("<"))
        assertEquals(RegisterComparison.LE, RegisterComparison.fromRepr("<="))
        assertFailsWith(IllegalArgumentException::class) {
            RegisterComparison.fromRepr("!")
        }
    }

    @Test
    fun testInstruction() {
        assertEquals(
                Instruction(
                        "bc",
                        RegisterOperation.INC,
                        5,
                        "ab",
                        RegisterComparison.GT,
                        1),
                Instruction.fromRepr("bc inc 5 if ab > 1"))
    }
}

class RegistersTest {
    @Test
    fun testProcessInstructions() {
        val input = """
            b inc 5 if a > 1
            a inc 1 if b < 5
            c dec -10 if a >= 1
            c inc -20 if c == 10
            """.trimIndent().lines().map { Instruction.fromRepr(it) }
        val expected = Pair(mapOf(
                "a" to 1,
                "c" to -10
        ), 10)
        assertEquals(expected, processInstructions(input))
    }
}
package com.gwax.aoc2017.day18

import com.gwax.aoc2017.day15.withTiming
import java.math.BigInteger

const val SND_KEY = '\u0007'  // Bell control character
typealias Memory = MutableMap<Char, Long>
typealias Program = List<Operation>

class RecoverException(val snd: Long) : Exception() {
    override val message: String?
        get() = "Recovered $snd"
}

interface Value {
    fun retrieve(memory: Memory): Long
}
fun value(input: String): Value =
        try {
            Constant(input.toLong())
        } catch (_: NumberFormatException) {
            Register(input.first())
        }

data class Register(val name: Char) : Value {
    override fun retrieve(memory: Memory) = memory.getOrDefault(name, 0)
    fun put(memory: Memory, value: Long) {
        memory[name] = value
    }
}

data class Constant(val value: Long) : Value {
    override fun retrieve(memory: Memory) = value
}

interface Operation {
    fun perform(memory: Memory): Int
}
fun operation(args: List<String>): Operation = when(args[0]) {
    "snd" -> Snd(value(args[1]))
    "set" -> Set(value(args[1]) as Register, value(args[2]))
    "add" -> Add(value(args[1]) as Register, value(args[2]))
    "mul" -> Mul(value(args[1]) as Register, value(args[2]))
    "mod" -> Mod(value(args[1]) as Register, value(args[2]))
    "rcv" -> Rcv(value(args[1]) as Register)
    "jgz" -> Jgz(value(args[1]), value(args[2]))
    else -> throw IllegalArgumentException(args.joinToString())
}

data class Snd(val x: Value) : Operation {
    override fun perform(memory: Memory): Int {
        memory[SND_KEY] = x.retrieve(memory)
        return 1
    }
}

data class Set(val x: Register, val y: Value) : Operation {
    override fun perform(memory: Memory): Int {
        x.put(memory, y.retrieve(memory))
        return 1
    }
}

data class Add(val x: Register, val y: Value) : Operation {
    override fun perform(memory: Memory): Int {
        x.put(memory, x.retrieve(memory) + y.retrieve(memory))
        return 1
    }
}

data class Mul(val x: Register, val y: Value) : Operation {
    override fun perform(memory: Memory): Int {
        x.put(memory, x.retrieve(memory) * y.retrieve(memory))
        return 1
    }
}

data class Mod(val x: Register, val y: Value) : Operation {
    override fun perform(memory: Memory): Int {
        x.put(memory, x.retrieve(memory) % y.retrieve(memory))
        return 1
    }
}

data class Rcv(val x: Register) : Operation {
    override fun perform(memory: Memory): Int {
        if (x.retrieve(memory) == 0L) return 1
        throw RecoverException(memory.getOrDefault(SND_KEY, 0L))
    }
}

data class Jgz(val x: Value, val y: Value) : Operation {
    override fun perform(memory: Memory): Int {
        return if (x.retrieve(memory) > 0 )
            y.retrieve(memory).toInt()
        else
            1
    }
}

fun parseProgram(input: String): Program =
        input.lines()
            .map { it.split(" ") }
            .map(::operation)

fun execute(program: Program) {
    var pointer = 0
    val memory = mutableMapOf<Char, Long>()
    while (pointer >= 0 && pointer < program.size) {
        val operation = program[pointer]
        val jmp = operation.perform(memory)
        pointer += jmp
    }
}

fun main(args: Array<String>) = withTiming {
    val program = parseProgram(input)
    try {
        execute(program)
    } catch (recover: RecoverException) {
        println(recover.snd)
    }
}


val input = """
set i 31
set a 1
mul p 17
jgz p p
mul a 2
add i -1
jgz i -2
add a -1
set i 127
set p 618
mul p 8505
mod p a
mul p 129749
add p 12345
mod p a
set b p
mod b 10000
snd b
add i -1
jgz i -9
jgz a 3
rcv b
jgz b -1
set f 0
set i 126
rcv a
rcv b
set p a
mul p -1
add p b
jgz p 4
snd a
set a b
jgz 1 3
snd b
set f 1
add i -1
jgz i -11
snd a
jgz f -16
jgz a -19
""".trimIndent()
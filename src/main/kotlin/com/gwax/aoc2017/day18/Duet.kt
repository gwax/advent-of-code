package com.gwax.aoc2017.day18

import com.gwax.aoc2017.day15.withTiming

typealias Memory = MutableMap<Char, Long>

sealed class Value {
    abstract fun retrieve(memory: Memory): Long
}
fun value(input: String): Value =
        try {
            Constant(input.toLong())
        } catch (_: NumberFormatException) {
            Register(input.first())
        }
data class Register(val name: Char): Value() {
    override fun retrieve(memory: Memory) = memory.getOrDefault(name, 0)
    fun put(memory: Memory, value: Long) {
        memory[name] = value
    }
}
data class Constant(val value: Long) : Value() {
    override fun retrieve(memory: Memory) = value
}

sealed class Operation
data class Snd(val x: Value) : Operation()
data class Set(val x: Register, val y: Value) : Operation()
data class Add(val x: Register, val y: Value) : Operation()
data class Mul(val x: Register, val y: Value) : Operation()
data class Mod(val x: Register, val y: Value) : Operation()
data class Rcv(val x: Register) : Operation()
data class Jgz(val x: Value, val y: Value) : Operation()
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

enum class Status { Active, Blocked, Terminated }

open class Processor(val instructions: List<Operation>, var exPointer: Int, val registers: Memory, val readBuffer: MutableList<Long>, val writeBuffer: MutableList<Long>) {
    var overrideStatus: Status? = null
    fun status(): Status {
        val currentOverride = overrideStatus
        return when {
            currentOverride != null -> currentOverride
            exPointer < 0 || exPointer >= instructions.size -> Status.Terminated
            instructions[exPointer] is Rcv && readBuffer.isEmpty() -> Status.Blocked
            else -> Status.Active
        }
    }
    open fun receive(op: Rcv) {
        println(readBuffer.last())
        overrideStatus = Status.Terminated
    }
    open fun send(op: Snd) {
        writeBuffer.add(op.x.retrieve(registers))
    }
    fun execute() {
        if (status() != Status.Active) return
        val op = instructions[exPointer]
        when(op) {
            is Snd -> {
                send(op)
                exPointer++
            }
            is Set -> {
                op.x.put(registers, op.y.retrieve(registers))
                exPointer++
            }
            is Add -> {
                op.x.put(registers, op.x.retrieve(registers) + op.y.retrieve(registers))
                exPointer++
            }
            is Mul -> {
                op.x.put(registers, op.x.retrieve(registers) * op.y.retrieve(registers))
                exPointer++
            }
            is Mod -> {
                op.x.put(registers, op.x.retrieve(registers) % op.y.retrieve(registers))
                exPointer++
            }
            is Rcv -> {
                receive(op)
                exPointer++
            }
            is Jgz -> {
                if (op.x.retrieve(registers) > 0)
                    exPointer += op.y.retrieve(registers).toInt()
                else
                    exPointer++
            }
        }
    }
}

class DuetProcessor(instructions: List<Operation>, exPointer: Int, registers: Memory, readBuffer: MutableList<Long>, writeBuffer: MutableList<Long>) :
        Processor(instructions, exPointer, registers, readBuffer, writeBuffer) {
    val name = registers['p']
    var sendCount = 0
    override fun receive(op: Rcv) {
        val newValue = readBuffer.removeAt(0)
//        println("$name:Receive ${op.x}:$newValue")
        op.x.put(registers, newValue)
    }

    override fun send(op: Snd) {
//        println("$name:Send ${op.x}${op.x.retrieve(registers)}")
        super.send(op)
        sendCount++
    }
}

fun parseProgram(input: String): List<Operation> =
        input.lines()
            .map { it.split(" ") }
            .map(::operation)

fun processorLoop(instructions: List<Operation>) {
    val buffer = mutableListOf<Long>()
    val processor = Processor(instructions, 0, mutableMapOf(), buffer, buffer)
    do {
        processor.execute()
    } while (processor.status() == Status.Active)
}

fun processorDualLoop(instructions: List<Operation>) {
    val queue0 = mutableListOf<Long>()
    val queue1 = mutableListOf<Long>()
    val processor0 = DuetProcessor(
            instructions,
            0,
            mutableMapOf('p' to 0L),
            queue0,
            queue1)
    val processor1 = DuetProcessor(
            instructions,
            0,
            mutableMapOf('p' to 1L),
            queue1,
            queue0)
    do {
        processor0.execute()
        processor1.execute()
    } while (processor0.status() == Status.Active || processor1.status() == Status.Active)
    println(processor1.sendCount)
}

fun main(args: Array<String>) = withTiming {
    val program = parseProgram(input)
    processorLoop(program)
    processorDualLoop(program)
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
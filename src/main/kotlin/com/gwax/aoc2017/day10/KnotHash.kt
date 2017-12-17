package com.gwax.aoc2017.day10

data class StepResult(val position: Int, val skip: Int)

class DataCircle(val size: Int) {
    constructor(elements: List<Int>) : this(elements.size) {
        _elements = elements.toMutableList()
    }
    private var _elements = (0 until size).toMutableList()
    val elements: List<Int>
        get() = _elements.toList()

    override fun equals(other: Any?): Boolean {
        if (other is DataCircle) return this.elements == other.elements
        return super.equals(other)
    }

    override fun toString(): String = _elements.joinToString(prefix = "(~", separator = "-", postfix = "~)")

    fun rotate(distance: Int) {
        val rotation = if (distance < 0) size + distance else distance
        _elements = (_elements.drop(rotation) + _elements.dropLast(size - rotation)).toMutableList()
    }
    fun tieKnot(position: Int, length: Int) {
        if (length > size) return
        rotate(position)
        _elements = (_elements.subList(0, length).reversed() + _elements.drop(length)).toMutableList()
        rotate(-position)
    }

    fun hashStep(position: Int, length: Int, skip: Int): StepResult {
        if (length > size) return StepResult(position, skip)
        tieKnot(position, length)
        return StepResult((position + length + skip) % size, skip + 1)
    }

    fun knotHash(lengths: List<Int>, times: Int = 1): StepResult {
        var step = StepResult(0, 0)
        repeat(times) {
            lengths.forEach {
                step = hashStep(step.position, it, step.skip)
            }
        }
        return step
    }
}

fun denseHash(circle: DataCircle): String =
        circle.elements.chunked(16, {
            it.reduce { a, b -> a xor b }
                    .toString(16)
                    .padStart(2, '0') })
                .joinToString("")

fun hashLengths(input: String): List<Int> =
        input.map { it.toInt() }.toList() + listOf(17, 31, 73, 47, 23)

fun main(args: Array<String>) {
    val lengths1 = input.split(",").map { it.toInt() }
    val circle1 = DataCircle(256)
    circle1.knotHash(lengths1)
    println(circle1.elements[0] * circle1.elements[1])

    val lengths2 = hashLengths(input)
    val circle2 = DataCircle(256)
    circle2.knotHash(lengths2, 64)
    println(denseHash(circle2))
}

val input = "147,37,249,1,31,2,226,0,161,71,254,243,183,255,30,70"
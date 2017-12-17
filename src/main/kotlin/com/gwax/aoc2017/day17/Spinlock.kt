package com.gwax.aoc2017.day17

import com.gwax.aoc2017.day15.withTiming

class Buffer(val contents: List<Int>, val position: Int) {
    val lastValue = contents[position]
    fun newBuffer(steps: Int): Buffer {
        val newPosition = (position + steps) % contents.size + 1
        var newList = contents.subList(0, newPosition) +
                listOf(lastValue + 1) +
                contents.subList(newPosition, contents.size)
        return Buffer(newList, newPosition)
    }
}

class Buffer2(val position: Int, val size: Int, val value2: Int) {
    fun newBuffer(steps: Int): Buffer2 {
        val newPosition = (position + steps) % size + 1
        val newSize = size + 1
        val newValue2 = if (newPosition == 1) size else value2
        return Buffer2(newPosition, newSize, newValue2)
    }
}

fun main(args: Array<String>) = withTiming {
    var buf = Buffer(listOf(0), 0)
    do {
        buf = buf.newBuffer(input)
    } while (buf.lastValue < 2017)
    println("Found ${buf.contents[buf.position + 1]}")

    var buf2 = Buffer2(0,1,0)
    do {
        buf2 = buf2.newBuffer(input)
    } while (buf2.size <= 50_000_000)
    println(buf2.value2)
}

val input = 354
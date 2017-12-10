package com.gwax.aoc2017.day6

fun reallocate(banks: List<Int>): List<Int> {
    val maxvalue = banks.max() ?: return banks
    var i = banks.indexOf(maxvalue)
    val newbanks = banks.toMutableList()
    var blocks = newbanks[i]
    newbanks[i] = 0
    i = (i + 1) % newbanks.size
    while (blocks > 0) {
        newbanks[i] += 1
        blocks -= 1
        i = (i + 1) % newbanks.size
    }
    return newbanks.toList()
}

fun reallocsUntilLoop(banks: List<Int>): Int {
    val seen = mutableSetOf<List<Int>>()
    var currentbank = banks
    var count = 0
    while (!seen.contains(currentbank)) {
        seen.add(currentbank)
        count++
        currentbank = reallocate(currentbank)
    }
    return count
}

fun reallocCycleLength(banks: List<Int>): Int {
    val seen = mutableMapOf<List<Int>,Int>()
    var currentbank = banks
    var count = 0
    while (!seen.containsKey(currentbank)) {
        seen.set(currentbank, count)
        count++
        currentbank = reallocate(currentbank)
    }
    return count - seen.getOrDefault(currentbank, count)
}

fun main(args: Array<String>) {
    val banks = input.split("\\s+".toRegex()).map { it.toInt() }
    println(reallocsUntilLoop(banks))
    println(reallocCycleLength(banks))
}

val input = """
    4	1	15	12	0	9	9	5	5	8	7	3	14	5	12	3
""".trimIndent()
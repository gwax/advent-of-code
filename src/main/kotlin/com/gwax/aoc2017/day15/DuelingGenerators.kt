package com.gwax.aoc2017.day15

import org.apache.commons.lang3.time.StopWatch
import java.math.BigInteger

open class Generator(seed: Int, private val factor: Int) : Iterator<Int> {
    private var current = seed.toLong()
    override fun hasNext(): Boolean = true
    override fun next(): Int {
        current = (current * factor) % 2147483647
        return current.toInt()
    }
}

class FilteredGenerator(seed: Int, factor: Int, private val modCriteria: Int): Generator(seed, factor) {
    override fun next(): Int {
        while(true) {
            val superValue = super.next()
            if (superValue % modCriteria == 0) return superValue
        }
    }
}

fun Int.judgeString(): String = this.toString(2).padStart(32,'0').takeLast(16)

fun judgeGenerators(a: Iterator<Int>, b: Iterator<Int>, trials: Int): Int {
    var matches = 0
    repeat(trials, {
        val aValue = a.next()
        val bValue = b.next()
        if (aValue.judgeString() == bValue.judgeString()) matches++
    })
    return matches
}

fun withTiming(block: () -> Unit) {
    val stopWatch = StopWatch()
    stopWatch.start()
    block()
    stopWatch.stop()
    println("Completed in ${stopWatch.time} milliseconds")
}

fun main(args: Array<String>) = withTiming {
    println(judgeGenerators(
            Generator(aSeed, aFactor),
            Generator(bSeed, bFactor),
            40_000_000))
    println(judgeGenerators(
            FilteredGenerator(aSeed, aFactor,4),
            FilteredGenerator(bSeed, bFactor,8),
            5_000_000))
}

val aSeed = 722
val aFactor = 16807
val bSeed = 354
val bFactor = 48271
package com.gwax.aoc2017.day15

import java.math.BigInteger

class Generator(seed: Int, factor: Int) : Iterator<Int> {
    private var current = seed.toLong()
    private val factor = factor.toLong()
    override fun hasNext(): Boolean = true
    override fun next(): Int {
        current = (factor * current) % 2147483647
        return current.toInt()
    }
}

class FilteredGenerator(gen: Generator, modCriteria: Int) : Iterator<Int> {
    private val gen = gen
    private val modCriteria = modCriteria
    override fun hasNext(): Boolean = true
    override fun next(): Int {
        while (true) {
            val genValue = gen.next()
            if (genValue % modCriteria == 0) return genValue
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

fun main(args: Array<String>) {
    println(judgeGenerators(
            Generator(aSeed, aFactor),
            Generator(bSeed, bFactor),
            40_000_000))
    println(judgeGenerators(
            FilteredGenerator(
                    Generator(aSeed, aFactor),
                    4),
            FilteredGenerator(
                    Generator(bSeed, bFactor),
                    8),
            5_000_000))
}

val aSeed = 722
val aFactor = 16807
val bSeed = 354
val bFactor = 48271
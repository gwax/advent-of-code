package com.gwax.aoc2017.day3

import kotlin.math.abs
import kotlin.math.max

typealias Point = Pair<Int, Int>

fun Point.next(): Point {
    val (x, y) = this
    val layer = max(abs(x), abs(y))
    return when {
        y == -layer -> Point(x + 1, y)
        x == -layer -> Point(x, y - 1)
        y == layer -> Point(x - 1, y)
        else -> Point(x, y + 1)
    }
}

fun Point.manhattan(): Int = abs(this.first) + abs(this.second)

fun spiralOut(steps: Int): Point {
    var location = Point(0, 0)
    repeat(steps - 1,
            {_ -> location = location.next()})
    return location
}

val aroundOrigin = (-1..1).flatMap { x ->
    (-1..1).map { y -> Point(x,y) } }
        .filter { it != Point(0,0)}

fun Point.around(): List<Point> = aroundOrigin.map {
    Point(this.first + it.first, this.second + it.second) }

fun partTwo(target: Int): Int {
    var location = Point(0,0)
    var value = 1
    val memoryStore = mutableMapOf(location to value)
    while (value <= target) {
        location = location.next()
        value = location.around().map {
            memoryStore.getOrDefault(it, 0)
        }.sum()
        memoryStore.put(location, value)
    }
    return value
}

val input = 289326

fun main(args: Array<String>) {
    println(spiralOut(input).manhattan())
    println(partTwo(input))
}
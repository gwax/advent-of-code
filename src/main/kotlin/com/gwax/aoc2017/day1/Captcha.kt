package com.gwax.aoc2017.day1

private fun Char.toNumber() = this.toInt() - '0'.toInt()
fun String.rotate(distance: Int): String {
    val rotation = if (distance < 0) this.length + distance else distance
    return this.drop(rotation) + this.dropLast(this.length - rotation)
}

fun solveCaptcha(input: String, distance: Int): Int {
    return input.zip(input.rotate(distance))
            .filter {(a, b) -> a == b}
            .map {(a, _) -> a.toNumber()}
            .sum()
}

fun solveFirst(input: String) = solveCaptcha(input, 1)
fun solveSecond(input: String) = solveCaptcha(input, input.length / 2)

fun main(args: Array<String>) {
    val input = args.first()
    println(solveFirst(input))
    println(solveSecond(input))
}
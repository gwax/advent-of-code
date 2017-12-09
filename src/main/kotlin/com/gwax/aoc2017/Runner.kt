package com.gwax.aoc2017

import com.gwax.aoc2017.day1.solveCaptcha

fun main(args: Array<String>) {
    val cmd = args.first()
    val cmdargs: Array<String> = args.drop(1).toTypedArray()
    when (cmd) {
        "day1" -> com.gwax.aoc2017.day1.main(cmdargs)
        else -> println("Unknown Command: " + cmd)
    }
}
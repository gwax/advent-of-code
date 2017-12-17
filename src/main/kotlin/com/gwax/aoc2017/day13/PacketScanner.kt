package com.gwax.aoc2017.day13

typealias Firewall = Map<Int, Int>

fun buildFirewall(input: String): Firewall =
        input.lines()
                .map {
                    it.split(":")
                            .map { it.trim().toInt() }
                }
                .map { it[0] to it[1] }
                .toMap()

fun scannerPosition(range: Int, time: Int): Int {
    val scanloop = 2 * (range - 1)
    val progression = time % scanloop
    return when {
        progression < range -> progression
        else -> scanloop - progression
    }
}

fun hits(firewall: Firewall, goTime: Int): List<Int> =
    firewall
            .filter {
                (depth, range) -> scannerPosition(range, goTime + depth) == 0
            }
            .keys
            .toList()

fun severity(firewall: Firewall, hitSpots: List<Int>): Int =
    hitSpots.map { it * (firewall[it] ?: 0) }.sum()

fun doesHit(firewall: Firewall, goTime: Int): Boolean {
    firewall.forEach { (depth, range) ->
        if (scannerPosition(range, goTime + depth) == 0) return true
    }
    return false
}

const val TIME_LIMIT = 1_000_000_000
fun findTime(firewall: Firewall): Int {
    (0..TIME_LIMIT).forEach { if (!doesHit(firewall, it)) return it }
    return -1
}

fun main(args: Array<String>) {
    val firewall = buildFirewall(input)
    val hitSpots = hits(firewall, 0)
    println(severity(firewall, hitSpots))
    println(findTime(firewall))
}

val input = """
0: 3
1: 2
2: 6
4: 4
6: 4
8: 8
10: 9
12: 8
14: 5
16: 6
18: 8
20: 6
22: 12
24: 6
26: 12
28: 8
30: 8
32: 10
34: 12
36: 12
38: 8
40: 12
42: 12
44: 14
46: 12
48: 14
50: 12
52: 12
54: 12
56: 10
58: 14
60: 14
62: 14
64: 14
66: 17
68: 14
72: 14
76: 14
80: 14
82: 14
88: 18
92: 14
98: 18
""".trimIndent()
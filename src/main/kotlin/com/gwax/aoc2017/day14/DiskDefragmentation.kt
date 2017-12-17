package com.gwax.aoc2017.day14

import com.gwax.aoc2017.day10.DataCircle
import com.gwax.aoc2017.day10.denseHash
import com.gwax.aoc2017.day10.hashLengths

fun hexToBits(hex: String): List<Boolean> =
        hex.flatMap {
            it.toString()
                    .toInt(16)
                    .toString(2)
                    .padStart(4, '0')
                    .map { it == '1' } }

fun hashBits(input: String): List<Boolean> {
    val lengths = hashLengths(input)
    val circle = DataCircle(256)
    circle.knotHash(lengths, 64)
    val hex = denseHash(circle)
    return hexToBits(hex)
}

fun hashGrid(input: String): List<List<Boolean>> =
        (0..127).map { "$input-$it" }
                .map { hashBits(it) }

fun regionGrid(usedGrid: List<List<Boolean>>): List<List<Int>> {
    val grid = MutableList(usedGrid.size, { MutableList(usedGrid[0].size, { 0 }) })
    var nextRegion = 1
    fun handleCell(i: Int, j: Int, region: Int): Boolean {
        if (i < 0 || j < 0 || i >= usedGrid.size || j >= usedGrid[0].size) return false
        if (!usedGrid[i][j]) return false
        if (grid[i][j] != 0) return false
        grid[i][j] = region
        handleCell(i + 1, j, region)
        handleCell(i - 1, j, region)
        handleCell(i, j + 1, region)
        handleCell(i, j - 1, region)
        return true
    }
    usedGrid.forEachIndexed {
        i, row -> row.forEachIndexed {
        j, _ -> if (handleCell(i, j, nextRegion)) nextRegion++ } }
    return grid.map { it.toList() }.toList()
}

fun main(args: Array<String>) {
    val grid = hashGrid(input)
    val used = grid.map { it.count { it } }.sum()
    println(used)
    val regions = regionGrid(grid)
    val maxRegion = regions.map { it.max() ?: 0 }.max() ?: 0
    println(maxRegion)
}

val input = "uugsqrei"
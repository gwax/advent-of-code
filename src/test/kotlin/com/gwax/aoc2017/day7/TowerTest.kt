package com.gwax.aoc2017.day7

import org.junit.Test
import kotlin.test.assertEquals

class ParseTest {
    @Test
    fun testRegex() {
        assertEquals(
                listOf("pbga (66)", "pbga", "66", ""),
                nodeRegex.find("pbga (66)")?.groupValues)
        assertEquals(
                listOf("fwft (72) -> ktlj, cntj, xhth", "fwft", "72", "ktlj, cntj, xhth"),
                nodeRegex.find("fwft (72) -> ktlj, cntj, xhth")?.groupValues
        )
    }

    @Test
    fun parseOneNode() {
        assertEquals(
                listOf(Node("pbga", TowerProgram(66, null, mutableListOf()))),
                parseNodes("pbga (66)"))
        assertEquals(
                listOf(Node("fwft", TowerProgram(72, null, mutableListOf("ktlj", "cntj", "xhth")))),
                parseNodes("fwft (72) -> ktlj, cntj, xhth"))
    }

    @Test
    fun parseTwoNodes() {
        val input = """
            pbga (66)
            fwft (72) -> ktlj, cntj, xhth
            """.trimIndent()
        assertEquals(
                listOf(
                        Node("pbga", TowerProgram(66, null, mutableListOf())),
                        Node("fwft", TowerProgram(72, null, mutableListOf("ktlj", "cntj", "xhth")))),
                parseNodes(input))
    }

    @Test
    fun buildNodeMapTest() {
        val input = """
            a (1) -> b, c
            b (2)
            """.trimIndent()
        assertEquals(
                mapOf("a" to TowerProgram(1, null, mutableListOf("b", "c")),
                        "b" to TowerProgram(2, "a", mutableListOf()),
                        "c" to TowerProgram(null, "a", mutableListOf())),
                buildNodeMap(input)
        )
    }

    @Test
    fun towerWeightTest() {
        val nodestr = """
            a (1) -> b, c
            b (2)
            d (5)
            """.trimIndent()
        val nodeMap = buildNodeMap(nodestr)
        assertEquals(2, towerWeight("b", nodeMap))
        assertEquals(3, towerWeight("a", nodeMap))
        assertEquals(5, towerWeight("d", nodeMap))
    }

    @Test
    fun fixWeight() {
        val input = """
            pbga (66)
            xhth (57)
            ebii (61)
            havc (66)
            ktlj (57)
            fwft (72) -> ktlj, cntj, xhth
            qoyq (66)
            padx (45) -> pbga, havc, qoyq
            tknk (41) -> ugml, padx, fwft
            jptl (61)
            ugml (68) -> gyxo, ebii, jptl
            gyxo (61)
            cntj (57)
            """.trimIndent()

        assertEquals(60, com.gwax.aoc2017.day7.fixWeight("tknk", buildNodeMap(input), 0))
    }
}
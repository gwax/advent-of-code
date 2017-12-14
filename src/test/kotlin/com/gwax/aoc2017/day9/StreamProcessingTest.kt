package com.gwax.aoc2017.day9

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.test.assertEquals

class SequenceTest {
    @Test
    fun equalityTest() {
        assertEquals(Group(null), Group(null))
        assertEquals(Garbage(null, "abc"), Garbage(null, "abc"))
        val a = Group(null)
        a.children.addAll(listOf(Group(a), Garbage(a, "123")))
        val b = Group(null)
        b.children.addAll(listOf(Group(b), Garbage(b, "123")))
        assertEquals(a, b)
    }

    @Test
    fun builderTest() {
        val expected = Group(null)
        val child1 = Group(expected)
        expected.children.add(child1)
        child1.children.addAll(listOf(
                Group(child1),
                Garbage(child1, "abc"),
                Garbage(child1, "def")))
        val child2 = Garbage(expected, "ghi")
        expected.children.add(child2)
        assertEquals(expected, group {
            group {
                group {}
                garbage("abc")
                garbage("def")
            }
            garbage("ghi")
        })
    }

    @Test
    fun stringTest() {
        assertEquals("{}", group {}.toString())
        assertEquals("<abc>", garbage("abc").toString())
        assertEquals("{{},<abc>}", group { group {}; garbage("abc") }.toString())
    }
}

@RunWith(Parameterized::class)
class ParseStreamTest(private val input: String, private val expected: Sequence?) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters(name="{0}")
        fun data() : Collection<Array<out Any?>> {
            return listOf(
                    arrayOf("", null),
                    arrayOf("{}", group {}),
                    arrayOf("<>", garbage("")),
                    arrayOf("<a!bc>", garbage("ac")),
                    arrayOf("{{},{<a>}}", group { group {}; group { garbage("a") }})
            )
        }
    }

    @Test
    fun parseTest() {
        assertEquals(expected, parseStream(null, input.iterator()))
    }
}

@RunWith(Parameterized::class)
class ScoreSequence(private val input: String, private val expected: Int) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters(name="{0}->{1}")
        fun data() : Collection<Array<out Any?>> {
            return listOf(
                    arrayOf("{}", 1),
                    arrayOf("{{{}}}", 6),
                    arrayOf("{{},{}}", 5),
                    arrayOf("{{{},{},{{}}}}", 16),
                    arrayOf("{<a>,<a>,<a>,<a>}", 1),
                    arrayOf("{{<ab>},{<ab>},{<ab>},{<ab>}}", 9),
                    arrayOf("{{<!!>},{<!!>},{<!!>},{<!!>}}", 9),
                    arrayOf("{{<a!>},{<a!>},{<a!>},{<ab>}}", 3)
            )
        }
    }

    @Test
    fun scoreTest() {
        assertEquals(expected, score(parseStream(null, input.iterator())!!))
    }
}
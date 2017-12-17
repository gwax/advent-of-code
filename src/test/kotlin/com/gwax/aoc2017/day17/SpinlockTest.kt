package com.gwax.aoc2017.day17

import org.junit.Test
import kotlin.test.assertEquals

class SpinlockTest {
    @Test
    fun newBufferTest() {
        var buf = Buffer(listOf(0), 0)
        assertEquals(listOf(0), buf.contents)
        assertEquals(0, buf.position)
        buf = buf.newBuffer(3)
        assertEquals(listOf(0, 1), buf.contents)
        assertEquals(1, buf.position)
        buf = buf.newBuffer(3)
        assertEquals(listOf(0, 2, 1), buf.contents)
        assertEquals(1, buf.position)
    }

    @Test
    fun bufferSecondTest() {
        var buf = Buffer(listOf(0),0)
        buf = buf.newBuffer(3)
        assertEquals(1, buf.contents[1])
        buf = buf.newBuffer(3)
        assertEquals(2, buf.contents[1])
        buf = buf.newBuffer(3)
        assertEquals(2, buf.contents[1])
        buf = buf.newBuffer(3)
        assertEquals(2, buf.contents[1])
        buf = buf.newBuffer(3)
        assertEquals(5, buf.contents[1])
        buf = buf.newBuffer(3)
        assertEquals(5, buf.contents[1])
        buf = buf.newBuffer(3)
        assertEquals(5, buf.contents[1])
        buf = buf.newBuffer(3)
        assertEquals(5, buf.contents[1])
        buf = buf.newBuffer(3)
        assertEquals(9, buf.contents[1])
    }

    @Test
    fun buffer2SecondTest() {
        var buf = Buffer2(0,1,0)
        buf = buf.newBuffer(3)
        assertEquals(1, buf.value2)
        buf = buf.newBuffer(3)
        assertEquals(2, buf.value2)
        buf = buf.newBuffer(3)
        assertEquals(2, buf.value2)
        buf = buf.newBuffer(3)
        assertEquals(2, buf.value2)
        buf = buf.newBuffer(3)
        assertEquals(5, buf.value2)
        buf = buf.newBuffer(3)
        assertEquals(5, buf.value2)
        buf = buf.newBuffer(3)
        assertEquals(5, buf.value2)
        buf = buf.newBuffer(3)
        assertEquals(5, buf.value2)
        buf = buf.newBuffer(3)
        assertEquals(9, buf.value2)
    }
}
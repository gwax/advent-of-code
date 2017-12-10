package com.gwax.aoc2017.day4

import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PassphrasesTest {
    @Test fun validPassphraseTest() {
        assertTrue { validPassphrase("aa bb cc dd ee", false) }
        assertFalse { validPassphrase("aa bb cc dd aa", false) }
        assertTrue { validPassphrase("aa bb cc dd aaa", false) }
    }

    @Test fun validPassphraseSortedTest() {
        assertTrue { validPassphrase("abcde fghij", true) }
        assertFalse { validPassphrase("abcde xyz ecdab", true) }
        assertTrue { validPassphrase("a ab abc abd abf abj", true) }
        assertTrue { validPassphrase("iiii oiii ooii oooi oooo", true) }
        assertFalse { validPassphrase("oiii ioii iioi iiio", true) }
    }
}
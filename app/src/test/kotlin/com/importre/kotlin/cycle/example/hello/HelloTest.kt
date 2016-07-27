package com.importre.kotlin.cycle.example.hello

import org.junit.Test
import kotlin.test.assertEquals

class HelloTest {

    @Test
    fun testGreeting() {
        val actual = greeting("cycle")
        val expected = "Hello, cycle!"
        assertEquals(expected, actual)
    }
}

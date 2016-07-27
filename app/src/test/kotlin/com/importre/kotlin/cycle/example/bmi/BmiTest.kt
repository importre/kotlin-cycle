package com.importre.kotlin.cycle.example.bmi

import org.junit.Test
import kotlin.test.assertEquals

class BmiTest {

    @Test
    fun testCalculateBmi() {
        val minWeight = 40
        val minHeight = 140
        val weight = 70 - minWeight
        val height = 170 - minHeight
        val weightProps = Props(min = 40, max = 140, value = weight)
        val heightProps = Props(min = 140, max = 210, value = height)
        val actual = calculateBmi(weight, height, weightProps, heightProps)
        val expected = 24
        assertEquals(expected, actual.bmi)
    }
}


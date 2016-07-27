package com.importre.kotlin.cycle.example.bmi

data class State(val weight: Int, val height: Int, val bmi: Int)

data class Props(val min: Int, val max: Int, val value: Int)

fun calculateBmi(weight: Int, height: Int, weightProps: Props, heightProps: Props): State {
    val realHeight = height + heightProps.min
    val realWeight = weight + weightProps.min
    val heightMeters = realHeight * 0.01F
    val bmi = Math.round(realWeight / (heightMeters * heightMeters))
    return State(realWeight, realHeight, bmi)
}


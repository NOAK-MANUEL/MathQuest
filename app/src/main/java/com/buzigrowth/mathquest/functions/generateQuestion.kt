package com.buzigrowth.mathquest.functions

import kotlin.math.pow

data class Question(val text:String, val answer:Int)
fun generateQuestion(level: Int): Question {
    val digits = (level / 3).coerceIn(1, 8)       // 1..8 zeros, never overflows Int
    val max = 10.0.pow(digits).toInt()            // 10, 100, 1000, ...
    val operandCount = (level / 3 + 1).coerceIn(2, 6)  // always at least 2 numbers

    val values = generateNumbers(max = max, times = operandCount)

    return when (level % 3) {
        1 -> Question(values.joinToString(" + "), values.reduce { a, i -> a+i })
        2 -> Question(values.joinToString(" - "), values.reduce { acc, i -> acc-i  })
        else -> Question(values.joinToString(" * "),values.reduce { i, acc -> i*acc })
    }
}
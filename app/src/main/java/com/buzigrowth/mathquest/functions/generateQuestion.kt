package com.buzigrowth.mathquest.functions

import kotlin.math.pow

data class Question(val text:String, val answer:Int)
fun generateQuestion(level: Int,mode:Int=0): Question {
    val stage = level %9
    val times = if (stage%3==0) stage/3 else stage/3+1
    val digits = (times).coerceIn(1, 8)       // 1..8 zeros, never overflows Int
    val max = 10.0.pow(digits).toInt()            // 10, 100, 1000, ...
//    val operandCount = (stage / 3 ).coerceIn(2, 6)  // always at least 2 numbers

    val values = generateNumbers(max = max, times = 2+mode)
    //mode increases the digit from 2+2 to 2+4+3 . that's why it starts with zero and added

    return when (stage % 3) {
        1 -> Question(values.joinToString(" + "), values.reduce { a, i -> a+i })
        2 -> Question(values.joinToString(" - "), values.reduce { acc, i -> acc-i  })
        else -> Question(values.joinToString(" * "),values.reduce { i, acc -> i*acc })
    }
}


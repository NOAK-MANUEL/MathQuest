package com.buzigrowth.mathquest.functions

fun generateNumbers(max: Int, times: Int): List<Int> =
    List(times) { generateNumber(max = max) }
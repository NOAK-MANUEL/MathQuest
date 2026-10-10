package com.buzigrowth.mathquest.functions

fun generateNumber(max:Int):Int{
    return (Math.random()*max).toInt().coerceIn(1,max)
}
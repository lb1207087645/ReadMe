package com.graduation.android.readme.login

import kotlin.math.max

fun largerNumber(num1: Int, num2: Int): Int {

    return max(num1, num2)

}

fun main() {

    val a = 37
    val b = 40
    val value = largerNumber(a, b)
    println("large number==" + value)
}
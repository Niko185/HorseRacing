package com.example.horseracing.presentation

fun Int.toRaceTimeString(): String {
    val seconds = this / 1000
    val millis = this % 1000
    return "$seconds.${millis.toString().padStart(3, '0')} сек"
}
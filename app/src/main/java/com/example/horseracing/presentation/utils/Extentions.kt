package com.example.horseracing.presentation.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Int.millisToSecondsString(): String {
    val seconds = this.toFloat() / 1000
    return "%.2f сек".format(seconds)
}

fun Long.formatRaceDate(): String {
    val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
    return dateFormat.format(Date(this))
}
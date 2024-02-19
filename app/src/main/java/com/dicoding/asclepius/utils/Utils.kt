package com.dicoding.asclepius.utils

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private val converter by lazy{
    SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
}

fun Date.formatToString(): String{
    return converter.format(this)
}

fun Float.formatToString(): String{
    val decimalFormatter = DecimalFormat("##.#")
    return decimalFormatter.format(this)
}
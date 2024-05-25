package com.example.designsystem.ext

fun Long.formatNumberWithSpaces(): String {
    return this.toString().reversed().chunked(3).joinToString(" ").reversed()
}
package id.twynonymouse.core.utils

fun String?.default() = if (this.isNullOrBlank()) "" else this
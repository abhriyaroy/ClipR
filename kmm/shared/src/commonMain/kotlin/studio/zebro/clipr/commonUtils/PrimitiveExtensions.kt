package studio.zebro.clipr.commonUtils

fun Boolean.toLong() : Long = if (this) 1L else 0L

fun Long.toBoolean() : Boolean = this > 0
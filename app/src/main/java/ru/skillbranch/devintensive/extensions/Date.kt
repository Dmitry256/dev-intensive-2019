package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR


fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy)"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val diff = date.time - this.time
    return when (diff) {
        in 0 * SECOND..1 * SECOND -> "только что"
        in 1 * SECOND..45 * SECOND -> "несколько секунд назад"
        in 45 * SECOND..75 * SECOND -> "минуту назад"
        in 75 * SECOND..45 * MINUTE -> "${diff / MINUTE} минут${if (diff / MINUTE in 2..4) "ы" else if (diff / MINUTE == 1L) "у" else ""} назад"
        in 45 * MINUTE..75 * MINUTE -> "час назад"
        in 75 * MINUTE..22 * HOUR -> "${diff / HOUR} час${if (diff / HOUR in 2..4) "а" else if (diff / HOUR == 1L) "" else "ов"} назад"
        in 22 * HOUR..26 * HOUR -> "день назад"
        in 26 * HOUR..360 * DAY -> "${diff / DAY} ${if (diff / DAY in 2..4) "дня" else if (diff / DAY == 1L) "день" else "дней"} назад"
        else -> "более года назад"
    }

//    return this.toString()
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}
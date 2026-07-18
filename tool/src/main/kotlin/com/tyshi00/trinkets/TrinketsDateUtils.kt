package com.tyshi00.trinkets

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale

fun todayStr(): String = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)

private val YMD_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd", Locale.getDefault())
private val DMY_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.getDefault())
private val MDY_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.getDefault())

/** e.g. "2027/03/14", "14/03/2027", or "03/14/2027" depending on the user's preference. */
fun dateLabel(isoDate: String, format: DateFormat = DateFormat.MDY): String {
    val parsed = LocalDate.parse(isoDate, DateTimeFormatter.ISO_LOCAL_DATE)
    val formatter = when (format) {
        DateFormat.YMD -> YMD_FORMAT
        DateFormat.DMY -> DMY_FORMAT
        DateFormat.MDY -> MDY_FORMAT
    }
    return parsed.format(formatter)
}

/**
 * Positive = days until a future date, 0 = today, negative = days since a
 * past date. Used to render both "in N days" and "N days ago" countdowns.
 */
fun daysUntil(isoDate: String): Long {
    val target = LocalDate.parse(isoDate, DateTimeFormatter.ISO_LOCAL_DATE)
    return ChronoUnit.DAYS.between(LocalDate.now(), target)
}

/** Human-friendly countdown label: "Today", "In 12 days", "3 days ago", etc. */
fun countdownLabel(isoDate: String): String {
    val days = daysUntil(isoDate)
    return when {
        days == 0L -> "Today"
        days == 1L -> "Tomorrow"
        days == -1L -> "Yesterday"
        days > 1L -> "In $days days"
        else -> "${-days} days ago"
    }
}

/**
 * A live, second-by-second breakdown of the time remaining (or elapsed)
 * between now and midnight at the start of [isoDate]. Countdowns only
 * store a date, not a time of day, so the timer always counts down to
 * 00:00 on the target date, matching the plain day-based label above.
 */
data class CountdownTimer(
    val isPast: Boolean,
    val days: Long,
    val hours: Long,
    val minutes: Long,
    val seconds: Long,
) {
    /** e.g. "3d 04h 12m 45s" */
    fun shortLabel(): String {
        val d = days.toString()
        val h = hours.toString().padStart(2, '0')
        val m = minutes.toString().padStart(2, '0')
        val s = seconds.toString().padStart(2, '0')
        return "${d}d ${h}h ${m}m ${s}s"
    }
}

/** Computes the live countdown breakdown for [isoDate] as of right now. */
fun countdownTimerFor(isoDate: String): CountdownTimer {
    val targetMidnight = LocalDate.parse(isoDate, DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay()
    val now = LocalDateTime.now()
    val isPast = now.isAfter(targetMidnight)
    val start = if (isPast) targetMidnight else now
    val end = if (isPast) now else targetMidnight

    val totalSeconds = ChronoUnit.SECONDS.between(start, end)
    val days = totalSeconds / 86400
    val hours = (totalSeconds % 86400) / 3600
    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60
    return CountdownTimer(isPast = isPast, days = days, hours = hours, minutes = minutes, seconds = seconds)
}

private val TWELVE_HOUR_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("h:mm a", Locale.getDefault())
private val TWENTY_FOUR_HOUR_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())

/** e.g. "12:00 AM" or "00:00", used wherever a specific clock time needs to be shown. */
fun timeLabel(time: LocalTime, format: TimeFormat): String {
    val formatter = when (format) {
        TimeFormat.AM_PM -> TWELVE_HOUR_FORMAT
        TimeFormat.HOUR_24 -> TWENTY_FOUR_HOUR_FORMAT
    }
    return time.format(formatter)
}

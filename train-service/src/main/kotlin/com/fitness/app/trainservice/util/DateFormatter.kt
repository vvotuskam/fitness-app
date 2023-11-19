package com.fitness.app.trainservice.util

import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Component
class DateFormatter {

    companion object {
        private const val DATE_PATTERN = "dd-MM-yyyy"
        private const val TIME_PATTERN = "hh:mm"
    }

    fun toDate(localDateTime: LocalDateTime): String {
        return localDateTime.format(DateTimeFormatter.ofPattern(DATE_PATTERN))
    }

    fun toTime(localDateTime: LocalDateTime): String {
        return localDateTime.format(DateTimeFormatter.ofPattern(TIME_PATTERN))
    }
}
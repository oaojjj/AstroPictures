package com.oseong.ifeelalive.utils

import java.text.SimpleDateFormat
import java.util.*

object Utils {
    const val ONE_WEEK_TO_MILLS = 604800017L
    const val TWO_WEEKS_TO_MILLS = 1209600000L

    fun dateToString(timeMillis: Long): String {
        return SimpleDateFormat(
            "yyyy-MM-dd", Locale.getDefault()
        ).format(timeMillis)
    }

    fun getMillsFromMinusWeek(date: Long, mills: Long): Long = date.minus(mills)

}
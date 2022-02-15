package com.oseong.ifeelalive.utils

import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun getTodayDate(): String {
        return SimpleDateFormat(
            "yyyy-MM-dd", Locale.getDefault()
        ).format(System.currentTimeMillis())
    }
}
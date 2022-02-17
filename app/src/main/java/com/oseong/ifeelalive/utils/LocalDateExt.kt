package com.oseong.ifeelalive.utils

import org.threeten.bp.LocalDate

fun LocalDate.minusTwoWeeks(): LocalDate = this.minusWeeks(2)
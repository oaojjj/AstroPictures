package com.oseong.astropictures.utils

import org.threeten.bp.LocalDateTime

fun LocalDateTime.minusTwoWeeks(): LocalDateTime = this.minusWeeks(2)
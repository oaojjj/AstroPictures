package com.oseong.ifeelalive.utils

import androidx.fragment.app.Fragment

fun Fragment.setStatusBarColor(newColor: Int): Int =
    with(requireActivity().window) {
        val prevStatusColor = statusBarColor
        statusBarColor = newColor
        prevStatusColor
    }

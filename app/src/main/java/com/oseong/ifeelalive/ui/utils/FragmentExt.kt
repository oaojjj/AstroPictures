package com.oseong.ifeelalive.ui.utils

import androidx.fragment.app.Fragment

fun Fragment.setStatusBarColor(newColor: Int): Int =
    with(requireActivity().window) {
        val prevStatusColor = statusBarColor
        statusBarColor = newColor
        prevStatusColor
    }

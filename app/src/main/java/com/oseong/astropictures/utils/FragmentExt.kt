package com.oseong.astropictures.utils

import androidx.fragment.app.Fragment

fun Fragment.setStatusBarColor(newColor: Int) =
    with(requireActivity().window) {
        statusBarColor = newColor
    }

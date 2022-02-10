package com.oseong.ifeelalive.ui.picture

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.oseong.ifeelalive.R
import com.oseong.ifeelalive.ui.utils.setStatusBarColor

class AstronomyPictureFragment : Fragment() {

    private var statusColor = Color.BLACK

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_astronomy_picture, container, false)
    }

    override fun onResume() {
        super.onResume()

        with(requireActivity()) {
            statusColor = setStatusBarColor(resources.getColor(android.R.color.transparent))
            (this as AppCompatActivity).supportActionBar?.hide()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        setStatusBarColor(statusColor)
    }

    companion object {
        const val FRAGMENT_TAG = "AstronomyPictureFragment"
    }
}
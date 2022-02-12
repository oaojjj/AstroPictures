package com.oseong.ifeelalive.ui.astropicture

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.oseong.ifeelalive.R
import com.oseong.ifeelalive.utils.setStatusBarColor

class AstronomyPictureFragment : Fragment() {

    private var statusColor = Color.BLACK

    override fun onAttach(context: Context) {
        super.onAttach(context)
        with(requireActivity()) {
            statusColor = setStatusBarColor(resources.getColor(android.R.color.transparent))
            (this as AppCompatActivity).supportActionBar?.hide()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_astronomy_picture, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        setStatusBarColor(statusColor)
    }

    companion object {
        const val FRAGMENT_TAG = "AstronomyPictureFragment"
    }
}
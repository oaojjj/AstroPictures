package com.oseong.ifeelalive.ui.astropicturedetail

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.oseong.ifeelalive.R
import com.oseong.ifeelalive.data.AstroPicture
import com.oseong.ifeelalive.databinding.FragmentAstroPictureDetailBinding
import com.oseong.ifeelalive.utils.setStatusBarColor

class AstroPictureDetailFragment : Fragment() {

    private var _binding: FragmentAstroPictureDetailBinding? = null
    private val binding get() = _binding!!

    private var statusColor = Color.BLACK
    private val astroPicture: AstroPicture by lazy { arguments?.getSerializable("item") as AstroPicture }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val animation =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)

        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_astro_picture_detail,
            container,
            false
        )
        return with(binding) {
            this.item = astroPicture
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        setStatusBarColor(statusColor)
        _binding = null
    }

    companion object {
        const val FRAGMENT_TAG = "AstronomyPictureFragment"
    }
}
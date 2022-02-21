package com.oseong.ifeelalive.astropicturedetail

import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.appbar.AppBarLayout
import com.oseong.ifeelalive.R
import com.oseong.ifeelalive.data.AstroPicture
import com.oseong.ifeelalive.databinding.FragmentAstroPictureDetailBinding
import com.oseong.ifeelalive.utils.setStatusBarColor
import kotlin.math.abs

class AstroPictureDetailFragment : Fragment() {

    private var _binding: FragmentAstroPictureDetailBinding? = null
    private val binding get() = _binding!!

    private val astroPicture: AstroPicture by lazy { arguments?.getSerializable("item") as AstroPicture }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        with(requireActivity()) {
            setStatusBarColor(resources.getColor(android.R.color.transparent))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val animation =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)

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

            appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                if (abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                    //  Collapsed
                    toolbarTitle.setTextAppearance(R.style.collapsedTitleStyle)
                    // style에서는 왜 설정이 안되는가?..
                    toolbarTitle.maxLines = 1
                } else {
                    //Expanded
                    toolbarTitle.setTextAppearance(R.style.expandedTitleStyle)
                    toolbarTitle.maxLines = 2
                }
            })

            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageTransitionName = arguments?.getString("name")
        val titleTransitionName = arguments?.getString("title")

        ViewCompat.setTransitionName(binding.ivImage, imageTransitionName)
        ViewCompat.setTransitionName(binding.toolbarTitle, titleTransitionName)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val FRAGMENT_TAG = "AstronomyPictureFragment"
    }
}
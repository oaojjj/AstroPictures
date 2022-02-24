package com.oseong.ifeelalive.ui.astropicturedetail

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.color.MaterialColors
import com.google.android.material.transition.MaterialContainerTransform
import com.oseong.ifeelalive.R
import com.oseong.ifeelalive.data.AstroPicture
import com.oseong.ifeelalive.databinding.FragmentAstroPictureDetailBinding
import com.oseong.ifeelalive.utils.setStatusBarColor
import timber.log.Timber
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
        initTransitionElement()
    }

    private fun initTransitionElement() {
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            duration = 650
            drawingViewId = R.id.nav_host
            //containerColor = MaterialColors.getColor(requireActivity().findViewById(android.R.id.content), R.attr.colorSurface)
            fadeMode = MaterialContainerTransform.FADE_MODE_OUT
            isElevationShadowEnabled = true
        }

        /*with(sharedElementEnterTransition as MaterialContainerTransform) {
            addListener(object : TransitionListenerAdapter() {
                override fun onTransitionEnd(transition: Transition) {
                    removeListener(this)
                    if (isHoldAtEndEnabled) {
                        return
                    }
                    super.onTransitionEnd(transition)
                }
            })
        }*/
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

            fab.setOnClickListener {
                Timber.d("click-fab")
            }

            appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                appbar.post {
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
                }
            })

            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTransitionName()
    }

    private fun initTransitionName() {
        arguments?.getString("view").let {
            ViewCompat.setTransitionName(binding.coordinatorLayout, it)
        }
        /*
        arguments?.getString("image").let {
            ViewCompat.setTransitionName(binding.ivImage, it)
        }
        */
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val FRAGMENT_TAG = "AstronomyPictureFragment"
    }
}


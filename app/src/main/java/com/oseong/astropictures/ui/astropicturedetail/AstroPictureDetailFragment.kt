package com.oseong.astropictures.ui.astropicturedetail

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.transition.platform.MaterialArcMotion
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.oseong.astropictures.R
import com.oseong.astropictures.data.AstroPicture
import com.oseong.astropictures.databinding.FragmentAstroPictureDetailBinding
import com.oseong.astropictures.utils.setStatusBarColor
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class AstroPictureDetailFragment : Fragment() {

    private var _binding: FragmentAstroPictureDetailBinding? = null
    private val binding get() = _binding!!

    private val astroPicture: AstroPicture by lazy { arguments?.getSerializable("picture") as AstroPicture }
    private val vm: AstroPictureDetailViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        with(requireActivity()) {
            setStatusBarColor(resources.getColor(android.R.color.transparent))
        }
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
            item = astroPicture
            viewModel = vm
            this.lifecycleOwner = viewLifecycleOwner

            fab.setOnClickListener {
                vm.clickFavorite()
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
        initTransitionElement()
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

    private fun initTransitionElement() {
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            duration = 650L
            drawingViewId = R.id.nav_host
            fadeMode = MaterialContainerTransform.FADE_MODE_OUT
            setPathMotion(MaterialArcMotion())
            setAllContainerColors(Color.TRANSPARENT)
            scrimColor = resources.getColor(R.color.grey_800, null)
            isElevationShadowEnabled = false
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val FRAGMENT_TAG = "AstronomyPictureFragment"
    }
}


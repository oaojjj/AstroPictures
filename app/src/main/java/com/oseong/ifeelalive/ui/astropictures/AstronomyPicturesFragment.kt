package com.oseong.ifeelalive.ui.astropictures

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.oseong.ifeelalive.R
import com.oseong.ifeelalive.databinding.FragmentAstronomyPicturesBinding
import com.oseong.ifeelalive.ui.astropictures.adapter.AstroPicturesAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class AstronomyPicturesFragment : Fragment() {

    private var _binding: FragmentAstronomyPicturesBinding? = null
    private val binding get() = _binding!!

    private val vm: AstroPicturesViewModel by viewModels()
    private var astroAdapter: AstroPicturesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_astronomy_pictures, container, false
        )


        if (astroAdapter == null) {
            astroAdapter = AstroPicturesAdapter(vm)
        }

        return with(binding) {
            this.viewModel = vm
            lifecycleOwner = this@AstronomyPicturesFragment
            rvAstroPictures.run {
                this.adapter = astroAdapter
                layoutManager = LinearLayoutManager(activity)
            }
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.doOnPreDraw {
            parentFragment?.startPostponedEnterTransition()
        }
        vm.loadAstroPictures()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d("lifeCycle")
        _binding = null
    }

    companion object {
        const val FRAGMENT_TAG = "AstronomyPicturesFragment"
    }

}

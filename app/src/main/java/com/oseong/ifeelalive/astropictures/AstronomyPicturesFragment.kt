package com.oseong.ifeelalive.astropictures

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.oseong.ifeelalive.R
import com.oseong.ifeelalive.astropictures.adapter.AstroPicturesAdapter
import com.oseong.ifeelalive.databinding.FragmentAstronomyPicturesBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class AstronomyPicturesFragment : Fragment() {

    private var _binding: FragmentAstronomyPicturesBinding? = null
    private val binding get() = _binding!!

    private val vm: AstroPicturesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_astronomy_pictures, container, false
        )

        val astroAdapter = AstroPicturesAdapter()

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

        vm.error.observe(this, {
            if (it) {
                Toast.makeText(
                    requireContext(), "데이터를 불러오던 중, 에러가 발생했습니다.", Toast.LENGTH_SHORT
                ).show()
            }
        })
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

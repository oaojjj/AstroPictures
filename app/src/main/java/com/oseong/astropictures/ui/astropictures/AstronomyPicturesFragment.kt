package com.oseong.astropictures.ui.astropictures

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.oseong.astropictures.R
import com.oseong.astropictures.ui.astropictures.adapter.AstroPicturesAdapter
import com.oseong.astropictures.databinding.FragmentAstronomyPicturesBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class AstronomyPicturesFragment : Fragment() {

    private var _binding: FragmentAstronomyPicturesBinding? = null
    private val binding get() = _binding!!

    private val vm: AstroPicturesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
            lifecycleOwner = viewLifecycleOwner

            rvAstroPictures.run {
                this.adapter = astroAdapter
                layoutManager = LinearLayoutManager(activity)
            }
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.message.observe(this, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })

        binding.swipeRefreshLayout.setOnRefreshListener {
            vm.refreshPictures()
            binding.swipeRefreshLayout.isRefreshing = false
        }
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

package com.oseong.ifeelalive.ui.astropictures

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.oseong.ifeelalive.R
import com.oseong.ifeelalive.databinding.FragmentAstronomyPicturesBinding
import com.oseong.ifeelalive.ui.astropictures.adapter.AstroPicturesAdapter
import com.oseong.ifeelalive.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

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

        val adapter = AstroPicturesAdapter()
        return with(binding) {
            this.viewModel = vm
            lifecycleOwner = this@AstronomyPicturesFragment
            rvAstroPictures.adapter = adapter
            rvAstroPictures.layoutManager = LinearLayoutManager(activity)
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 날짜 선택 기능 추가 생각해보기.
        val endDate: Long = System.currentTimeMillis()
        val startDate: Long = Utils.getMillsFromMinusWeek(endDate, Utils.ONE_WEEK_TO_MILLS)
        vm.getAstroPictures(startDate, endDate)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val FRAGMENT_TAG = "AstronomyPicturesFragment"
    }

}
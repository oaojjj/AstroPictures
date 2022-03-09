package com.oseong.astropictures.ui.favoritepictures

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.oseong.astropictures.R
import com.oseong.astropictures.data.AstroPicture
import com.oseong.astropictures.databinding.FragmentFavoritePicturesBinding
import com.oseong.astropictures.ui.favoritepictures.adapter.FavoritePicturesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritePicturesFragment : Fragment() {
    private var _binding: FragmentFavoritePicturesBinding? = null
    private val binding get() = _binding!!

    private val vm: FavoritePicturesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_favorite_pictures, container, false)

        val listAdapter = FavoritePicturesAdapter()

        return with(binding) {
            viewModel = vm
            lifecycleOwner = viewLifecycleOwner

            with(rvFavoritePictures) {
                adapter = listAdapter
                layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            }
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // dataBinding 사용하지 않고 리스트 업데이트 하기
        vm.items.observe(this, object : Observer<List<AstroPicture>?> {
            override fun onChanged(item: List<AstroPicture>?) {
                (binding.rvFavoritePictures.adapter as FavoritePicturesAdapter)
                    .submitList(item)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
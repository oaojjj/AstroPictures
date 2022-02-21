package com.oseong.ifeelalive.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.oseong.ifeelalive.R
import com.oseong.ifeelalive.databinding.FragmentHomeViewPagerBinding
import com.oseong.ifeelalive.main.adapter.AstroPicturesPagerAdapter

import com.oseong.ifeelalive.main.adapter.ASTRO_PICTURES_INDEX
import com.oseong.ifeelalive.main.adapter.FAVORITE_ASTRO_PICTURE_INDEX
import com.oseong.ifeelalive.utils.setStatusBarColor
import timber.log.Timber

class HomeViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeViewPagerBinding.inflate(inflater, container, false)
        return with(binding) {
            viewPager.adapter = AstroPicturesPagerAdapter(this@HomeViewPagerFragment)
            TabLayoutMediator(tabs, viewPager, object : TabLayoutMediator.TabConfigurationStrategy {
                override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                    tab.setIcon(getTabIcon(position))
                    tab.text = getTabTitle(position)
                }
            }).attach()

            (activity as AppCompatActivity).setSupportActionBar(toolbar)

            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        with(requireActivity()) {
            setStatusBarColor(resources.getColor(R.color.black))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d("lifeCycle")
    }

    private fun getTabIcon(pos: Int): Int {
        return when (pos) {
            ASTRO_PICTURES_INDEX -> R.drawable.ic_astro_pictures
            FAVORITE_ASTRO_PICTURE_INDEX -> R.drawable.ic_favorite_pictures
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(pos: Int): String? {
        return when (pos) {
            ASTRO_PICTURES_INDEX -> getString(R.string.astro_pictures)
            FAVORITE_ASTRO_PICTURE_INDEX -> getString(R.string.favorite_astro_pictures)
            else -> null
        }
    }

}
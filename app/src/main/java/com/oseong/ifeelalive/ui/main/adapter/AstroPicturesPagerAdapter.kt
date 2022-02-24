package com.oseong.ifeelalive.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.oseong.ifeelalive.ui.astropictures.AstronomyPicturesFragment
import com.oseong.ifeelalive.ui.favoritepictures.FavoritePicturesFragment
import java.lang.IndexOutOfBoundsException

const val ASTRO_PICTURES_INDEX = 0
const val FAVORITE_ASTRO_PICTURE_INDEX = 1

class AstroPicturesPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentCreators: Map<Int, () -> Fragment> = mapOf(
        ASTRO_PICTURES_INDEX to { AstronomyPicturesFragment() },
        FAVORITE_ASTRO_PICTURE_INDEX to { FavoritePicturesFragment() }
    )

    override fun getItemCount(): Int = tabFragmentCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }

}

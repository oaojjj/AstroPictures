package com.oseong.ifeelalive.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.oseong.ifeelalive.R
import com.oseong.ifeelalive.ui.pictures.AstronomyPicturesFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))
        showAstronomyPicturesFragment()
    }

    private fun showAstronomyPicturesFragment() {
        with(supportFragmentManager) {
            findFragmentByTag(AstronomyPicturesFragment.FRAGMENT_TAG) as AstronomyPicturesFragment?
                ?: beginTransaction().add(
                    R.id.fragment_container,
                    AstronomyPicturesFragment(),
                    AstronomyPicturesFragment.FRAGMENT_TAG
                ).commit()
        }
    }
}
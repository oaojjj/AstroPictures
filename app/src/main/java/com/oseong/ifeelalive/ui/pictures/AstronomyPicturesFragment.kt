package com.oseong.ifeelalive.ui.pictures

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.oseong.ifeelalive.R
import com.oseong.ifeelalive.databinding.FragmentAstronomyPicturesBinding
import com.oseong.ifeelalive.ui.picture.AstronomyPictureFragment

class AstronomyPicturesFragment : Fragment() {

    var _binding: FragmentAstronomyPicturesBinding? = null
    private val binding get() = _binding!!

    private fun showActionbar(string: String) {
        with((requireActivity() as AppCompatActivity).supportActionBar) {
            this?.title = string
            this?.show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_astronomy_pictures,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivPicture.setOnClickListener {
            showAstronomyPictureFragment()

        }
    }

    override fun onResume() {
        super.onResume()
        showActionbar(resources.getString(R.string.astronomy_pictures))
    }

    private fun showAstronomyPictureFragment() {
        with(parentFragmentManager) {
            findFragmentByTag(AstronomyPictureFragment.FRAGMENT_TAG) as AstronomyPictureFragment?
                ?: beginTransaction().replace(
                    R.id.fragment_container,
                    AstronomyPictureFragment(),
                    AstronomyPictureFragment.FRAGMENT_TAG
                ).addToBackStack(null).commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val FRAGMENT_TAG = "AstronomyPicturesFragment"
    }

}
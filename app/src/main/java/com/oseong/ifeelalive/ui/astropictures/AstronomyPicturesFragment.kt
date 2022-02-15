package com.oseong.ifeelalive.ui.astropictures

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.oseong.ifeelalive.R
import com.oseong.ifeelalive.data.AstroPicture
import com.oseong.ifeelalive.data.source.remote.api.NasaService
import com.oseong.ifeelalive.databinding.FragmentAstronomyPicturesBinding
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class AstronomyPicturesFragment : Fragment() {

    private var _binding: FragmentAstronomyPicturesBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var retrofit: Retrofit

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_astronomy_pictures, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retrofit.create(NasaService::class.java).getRandomAstroPictures().enqueue(object :
            Callback<List<AstroPicture>> {
            override fun onResponse(
                call: Call<List<AstroPicture>>,
                response: Response<List<AstroPicture>>
            ) {
                Timber.d(response.body().toString())
            }

            override fun onFailure(call: Call<List<AstroPicture>>, t: Throwable) {
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val FRAGMENT_TAG = "AstronomyPicturesFragment"
    }

}
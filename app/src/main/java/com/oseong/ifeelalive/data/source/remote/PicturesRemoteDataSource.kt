package com.oseong.ifeelalive.data.source.remote

import com.oseong.ifeelalive.data.AstroPicture
import com.oseong.ifeelalive.data.source.PicturesDataSource
import com.oseong.ifeelalive.data.source.remote.api.NasaService
import com.oseong.ifeelalive.utils.Utils
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class PicturesRemoteDataSource @Inject constructor(
    private val retrofit: Retrofit,
) : PicturesDataSource {
    override suspend fun getAstroPictures(
        startDate: Long, endDate: Long
    ): Response<List<AstroPicture>> {
        val service = retrofit.create(NasaService::class.java)
        return service.getAstroPictures(
            Utils.dateToString(startDate), Utils.dateToString(endDate)
        )
    }

}
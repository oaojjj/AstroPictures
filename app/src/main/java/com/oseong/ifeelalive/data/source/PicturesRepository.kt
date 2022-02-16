package com.oseong.ifeelalive.data.source

import com.oseong.ifeelalive.data.AstroPicture
import retrofit2.Response
import javax.inject.Inject

class PicturesRepository @Inject constructor(
    private val picturesRemoteDataSource: PicturesDataSource
) : PicturesDataSource {

    override suspend fun getAstroPictures(
        startDate: Long, endDate: Long
    ): Response<List<AstroPicture>> = picturesRemoteDataSource.getAstroPictures(startDate, endDate)
}
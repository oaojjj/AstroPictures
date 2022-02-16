package com.oseong.ifeelalive.data.source

import com.oseong.ifeelalive.data.AstroPicture
import retrofit2.Response

interface PicturesDataSource {
    suspend fun getAstroPictures(startDate: Long, endDate: Long): Response<List<AstroPicture>>
}
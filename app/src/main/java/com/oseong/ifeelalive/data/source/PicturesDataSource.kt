package com.oseong.ifeelalive.data.source

import com.oseong.ifeelalive.data.AstroPicture
import org.threeten.bp.LocalDate
import retrofit2.Response

interface PicturesDataSource {
    suspend fun getAstroPictures(startDate: LocalDate, endDate: LocalDate): Response<List<AstroPicture>>
}
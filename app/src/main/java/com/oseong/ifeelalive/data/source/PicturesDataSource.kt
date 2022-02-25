package com.oseong.ifeelalive.data.source

import com.oseong.ifeelalive.data.AstroPicture
import org.threeten.bp.LocalDate
import retrofit2.Response

interface PicturesDataSource {
    /**
     * @param startDate startDate
     * @param endDate endDate
     * ex) startDate ~ endDate -> 2020-02-02 ~ 2020-02-09
     */
    suspend fun getAstroPictures(startDate: LocalDate, endDate: LocalDate): Response<List<AstroPicture>>
}
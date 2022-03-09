package com.oseong.astropictures.data.source

import com.oseong.astropictures.data.AstroPicture
import org.threeten.bp.LocalDateTime
import retrofit2.Response

interface PicturesDataSource {
    /**
     * @param startDate startDate
     * @param endDate endDate
     * ex) startDate ~ endDate -> 2020-02-02 ~ 2020-02-09
     */
    suspend fun getAstroPictures(startDate: LocalDateTime, endDate: LocalDateTime): Response<List<AstroPicture>>
}
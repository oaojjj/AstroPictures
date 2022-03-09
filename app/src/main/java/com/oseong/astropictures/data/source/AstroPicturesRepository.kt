package com.oseong.astropictures.data.source

import com.oseong.astropictures.data.AstroPicture

import org.threeten.bp.LocalDateTime
import retrofit2.Response
import javax.inject.Inject

class AstroPicturesRepository @Inject constructor(
    private val picturesRemoteDataSource: PicturesDataSource
) : PicturesDataSource {

    override suspend fun getAstroPictures(
        startDate: LocalDateTime, endDate: LocalDateTime
    ): Response<List<AstroPicture>> = picturesRemoteDataSource.getAstroPictures(startDate, endDate)
}
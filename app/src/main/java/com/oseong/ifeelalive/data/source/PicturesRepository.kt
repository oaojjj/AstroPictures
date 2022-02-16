package com.oseong.ifeelalive.data.source

import com.oseong.ifeelalive.data.AstroPicture
import org.threeten.bp.LocalDate
import retrofit2.Response
import javax.inject.Inject

class PicturesRepository @Inject constructor(
    private val picturesRemoteDataSource: PicturesDataSource
) : PicturesDataSource {

    override suspend fun getAstroPictures(
        startDate: LocalDate, endDate: LocalDate
    ): Response<List<AstroPicture>> = picturesRemoteDataSource.getAstroPictures(startDate, endDate)
}
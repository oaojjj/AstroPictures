package com.oseong.ifeelalive.data.source.remote

import com.oseong.ifeelalive.data.AstroPicture
import com.oseong.ifeelalive.data.source.PicturesDataSource
import com.oseong.ifeelalive.data.source.remote.api.NasaService
import org.threeten.bp.LocalDate
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class PicturesRemoteDataSource @Inject constructor(
    private val retrofit: Retrofit,
) : PicturesDataSource {

    override suspend fun getAstroPictures(startDate: LocalDate, endDate: LocalDate)
            : Response<List<AstroPicture>> {
        val service = retrofit.create(NasaService::class.java)
        return service.getAstroPictures(
            startDate.toString(), endDate.toString()
        )
    }
}
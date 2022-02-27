package com.oseong.ifeelalive.data.source.remote

import com.oseong.ifeelalive.data.AstroPicture
import com.oseong.ifeelalive.data.source.PicturesDataSource
import com.oseong.ifeelalive.data.source.remote.api.NasaService
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class PicturesRemoteDataSource @Inject constructor(
    private val retrofit: Retrofit,
) : PicturesDataSource {

    override suspend fun getAstroPictures(startDate: LocalDateTime, endDate: LocalDateTime)
            : Response<List<AstroPicture>> {
        val service = retrofit.create(NasaService::class.java)
        return service.getAstroPictures(
            startDate.toLocalDate().toString(), endDate.toLocalDate().toString()
        )
    }
}
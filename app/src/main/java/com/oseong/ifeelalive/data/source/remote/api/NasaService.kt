package com.oseong.ifeelalive.data.source.remote.api

import com.oseong.ifeelalive.data.AstroPicture
import org.threeten.bp.LocalDate
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaService {
    @GET("planetary/apod")
    suspend fun getAstroPictures(
        @Query("start_date") startData: String,
        @Query("end_date") endData: String,
        @Query("thumbs") thumbs: Boolean = true
    ): Response<List<AstroPicture>>

    @GET("planetary/apod")
    suspend fun getAstroPictureFromData(
        @Query("date") date: String = LocalDate.now().toString()
    )


    @GET("planetary/apod")
    suspend fun getRandomAstroPictures(
        @Query("count") count: Int = 1,
        @Query("thumbs") thumbs: Boolean = true
    ): Response<List<AstroPicture>>
}
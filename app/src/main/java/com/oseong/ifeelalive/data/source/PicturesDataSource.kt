package com.oseong.ifeelalive.data.source

interface PicturesDataSource {
    suspend fun getAstroPictures()
}
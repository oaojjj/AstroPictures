package com.oseong.ifeelalive.data.source.local

import com.oseong.ifeelalive.data.source.PicturesDataSource
import kotlinx.coroutines.CoroutineDispatcher

class PicturesLocalDataSource(
    astroPictureDao: AstroPictureDao,
    ioDispatcher: CoroutineDispatcher
) : PicturesDataSource {
    override suspend fun getAstroPictures() {
        TODO("Not yet implemented")
    }
}
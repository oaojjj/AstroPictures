package com.oseong.ifeelalive.data.source

import kotlinx.coroutines.CoroutineDispatcher

class PicturesRepository(
    private val picturesLocalDataSource: PicturesDataSource,
    private val picturesRemoteDataSource: PicturesDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : PicturesDataSource {
    override suspend fun getAstroPictures() {

    }
}
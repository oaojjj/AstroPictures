package com.oseong.ifeelalive.data.source

import com.oseong.ifeelalive.data.AstroPicture
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class FavoritesPicRepository @Inject constructor(
    private val localFavoritesPicDataSource: FavoritesPicDataSource
) : FavoritesPicDataSource {
    override fun getFavoritePictures(): Flow<List<AstroPicture>> {
        return localFavoritesPicDataSource.getFavoritePictures()
    }

    override fun isFavorite(pictureUrl: String): Flow<Boolean> {
        Timber.d(pictureUrl)
        return localFavoritesPicDataSource.isFavorite(pictureUrl)
    }

    override suspend fun favorite(astroPicture: AstroPicture) {
        localFavoritesPicDataSource.favorite(astroPicture)
    }

    override suspend fun unFavorite(astroPicture: AstroPicture) {
        localFavoritesPicDataSource.unFavorite(astroPicture)
    }

}
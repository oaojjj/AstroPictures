package com.oseong.ifeelalive.data.source.local

import com.oseong.ifeelalive.data.AstroPicture
import com.oseong.ifeelalive.data.source.FavoritesPicDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesPicLocalDataSource @Inject constructor(
    private val dao: AstroPictureDao
) : FavoritesPicDataSource {
    override fun getFavoritePictures(): Flow<List<AstroPicture>> {
        return dao.getAllFavoritePictures()
    }

    override fun isFavorite(pictureUrl: String): Flow<Boolean> = dao.isFavorite(pictureUrl)

    override suspend fun favorite(astroPicture: AstroPicture) {
        dao.insertFavoritePicture(astroPicture)
    }

    override suspend fun unFavorite(astroPicture: AstroPicture) {
        dao.deleteFavoritePicture(astroPicture)
    }
}
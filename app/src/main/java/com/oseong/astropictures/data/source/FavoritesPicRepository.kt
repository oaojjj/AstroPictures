package com.oseong.astropictures.data.source

import com.oseong.astropictures.data.AstroPicture
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesPicRepository @Inject constructor(
    private val localFavoritesPicDataSource: FavoritesPicDataSource
) : FavoritesPicDataSource {
    override fun getFavoritePictures(): Flow<List<AstroPicture>> {
        return localFavoritesPicDataSource.getFavoritePictures()
    }

    override fun isFavorite(pictureUrl: String): Flow<Boolean> {
        return localFavoritesPicDataSource.isFavorite(pictureUrl)
    }

    override suspend fun favorite(astroPicture: AstroPicture) {
        localFavoritesPicDataSource.favorite(astroPicture)
    }

    override suspend fun unFavorite(astroPicture: AstroPicture) {
        localFavoritesPicDataSource.unFavorite(astroPicture)
    }

}
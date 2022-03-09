package com.oseong.astropictures.data.source

import com.oseong.astropictures.data.AstroPicture
import kotlinx.coroutines.flow.Flow

interface FavoritesPicDataSource {
    fun getFavoritePictures(): Flow<List<AstroPicture>>
    fun isFavorite(pictureUrl: String): Flow<Boolean>
    suspend fun favorite(astroPicture: AstroPicture)
    suspend fun unFavorite(astroPicture: AstroPicture)
}
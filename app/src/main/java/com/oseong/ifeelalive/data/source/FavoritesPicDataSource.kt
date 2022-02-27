package com.oseong.ifeelalive.data.source

import androidx.lifecycle.LiveData
import com.oseong.ifeelalive.data.AstroPicture
import kotlinx.coroutines.flow.Flow

interface FavoritesPicDataSource {
    fun getFavoritePictures(): Flow<List<AstroPicture>>
    fun isFavorite(pictureUrl: String): Flow<Boolean>
    suspend fun favorite(astroPicture: AstroPicture)
    suspend fun unFavorite(astroPicture: AstroPicture)
}
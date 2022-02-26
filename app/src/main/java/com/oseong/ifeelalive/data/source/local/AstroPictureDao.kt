package com.oseong.ifeelalive.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.oseong.ifeelalive.data.AstroPicture
import kotlinx.coroutines.flow.Flow

@Dao
interface AstroPictureDao {

    @Query("SELECT * FROM astro_pic_table")
    fun getAllFavoritePictures(): Flow<List<AstroPicture>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoritePicture(astroPicture: AstroPicture)

    @Delete
    fun deleteFavoritePicture(astroPicture: AstroPicture)

    @Query("SELECT EXISTS(SELECT 1 FROM astro_pic_table WHERE url = :pictureUrl LIMIT 1)")
    fun isFavorite(pictureUrl: String): Flow<Boolean>
}
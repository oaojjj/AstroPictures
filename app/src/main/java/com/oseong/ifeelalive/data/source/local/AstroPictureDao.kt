package com.oseong.ifeelalive.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.oseong.ifeelalive.data.AstroPicture

@Dao
interface AstroPictureDao {

    @Query("SELECT * FROM astro_pic_table")
    fun getAllFavoritePictures(): LiveData<List<AstroPicture>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoritePicture(astroPicture: AstroPicture)

    @Delete
    suspend fun deleteFavoritePicture(astroPicture: AstroPicture)
}
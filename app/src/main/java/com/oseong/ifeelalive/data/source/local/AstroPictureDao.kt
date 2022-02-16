package com.oseong.ifeelalive.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.oseong.ifeelalive.data.AstroPicture

@Dao
interface AstroPictureDao {

    @Query("SELECT * FROM astro_pic_table")
    fun getAllFavoritePictures(): LiveData<List<AstroPicture>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoritePicture(astroPicture: AstroPicture)

    @Delete
    fun deleteFavoritePicture(astroPicture: AstroPicture)
}
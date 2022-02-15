package com.oseong.ifeelalive.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oseong.ifeelalive.data.AstroPicture

@Database(entities = [AstroPicture::class], version = 1)
abstract class AstroPictureDatabase : RoomDatabase() {
    abstract fun AstroPictureDao(): AstroPictureDao
}
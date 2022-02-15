package com.oseong.ifeelalive.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.oseong.ifeelalive.data.AstroPicture

@Database(entities = [AstroPicture::class], version = 1)
abstract class AstroPictureDatabase : RoomDatabase() {
    abstract fun astroPictureDao(): AstroPictureDao

    companion object {

        private var instance: AstroPictureDatabase? = null

        fun getInstance(context: Context): AstroPictureDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AstroPictureDatabase::class.java,
                    "AstroPictures.db"
                ).build().apply { instance = this }
            }
        }
    }
}
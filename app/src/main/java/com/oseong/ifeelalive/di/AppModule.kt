package com.oseong.ifeelalive.di

import android.content.Context
import com.oseong.ifeelalive.data.source.PicturesDataSource
import com.oseong.ifeelalive.data.source.local.AstroPictureDatabase
import com.oseong.ifeelalive.data.source.local.PicturesLocalDataSource
import com.oseong.ifeelalive.data.source.remote.PicturesRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Qualifier
    annotation class RemotePicturesDataSource

    @Qualifier
    annotation class LocalPicturesDataSource

    @Singleton
    @RemotePicturesDataSource
    @Provides
    fun providePicturesRemoteDataSource(): PicturesDataSource {
        return PicturesRemoteDataSource()
    }

    @Singleton
    @LocalPicturesDataSource
    @Provides
    fun providePicturesLocalDataSource(
        database: AstroPictureDatabase,
        ioDispatcher: CoroutineDispatcher
    ): PicturesDataSource {
        return PicturesLocalDataSource(
            database.astroPictureDao(), ioDispatcher
        )
    }

    @Singleton
    @Provides
    fun provideAstroPictureDatabase(@ApplicationContext context: Context): AstroPictureDatabase {
        return AstroPictureDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO

}
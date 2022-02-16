package com.oseong.ifeelalive.di

import android.content.Context
import com.oseong.ifeelalive.data.source.PicturesDataSource
import com.oseong.ifeelalive.data.source.local.AstroPictureDatabase
import com.oseong.ifeelalive.data.source.remote.PicturesRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Qualifier
    annotation class RemotePicturesDataSource

    @Singleton
    @RemotePicturesDataSource
    @Provides
    fun providePicturesRemoteDataSource(
        retrofit: Retrofit,
    ): PicturesDataSource {
        return PicturesRemoteDataSource(retrofit)
    }

    @Singleton
    @Provides
    fun provideAstroPictureDatabase(@ApplicationContext context: Context): AstroPictureDatabase {
        return AstroPictureDatabase.getInstance(context)
    }
}
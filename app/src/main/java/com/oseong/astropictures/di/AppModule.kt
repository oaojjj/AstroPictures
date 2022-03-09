package com.oseong.astropictures.di

import android.content.Context
import com.oseong.astropictures.data.source.FavoritesPicDataSource
import com.oseong.astropictures.data.source.PicturesDataSource
import com.oseong.astropictures.data.source.local.AstroPictureDao
import com.oseong.astropictures.data.source.local.AstroPictureDatabase
import com.oseong.astropictures.data.source.local.FavoritesPicLocalDataSource
import com.oseong.astropictures.data.source.remote.PicturesRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Qualifier
    annotation class RemotePicturesDataSource

    /*
    @Qualifier
    annotation class LocalPicturesDataSource
    */

    @Qualifier
    annotation class LocalFavoritesPicDataSource

    @Singleton
    @Provides
    fun provideAstroPictureDatabase(@ApplicationContext context: Context): AstroPictureDatabase {
        return AstroPictureDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideAstroPictureDao(appDatabase: AstroPictureDatabase): AstroPictureDao {
        return appDatabase.astroPictureDao()
    }

    @Singleton
    @RemotePicturesDataSource
    @Provides
    fun providePicturesRemoteDataSource(
        retrofit: Retrofit,
    ): PicturesDataSource {
        return PicturesRemoteDataSource(retrofit)
    }

    @Singleton
    @LocalFavoritesPicDataSource
    @Provides
    fun provideFavoritePicLocalDataSource(
        dao: AstroPictureDao
    ): FavoritesPicDataSource {
        return FavoritesPicLocalDataSource(dao)
    }

}
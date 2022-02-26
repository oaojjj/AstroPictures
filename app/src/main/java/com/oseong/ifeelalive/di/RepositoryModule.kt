package com.oseong.ifeelalive.di

import com.oseong.ifeelalive.data.source.PicturesDataSource
import com.oseong.ifeelalive.data.source.AstroPicturesRepository
import com.oseong.ifeelalive.data.source.FavoritesPicDataSource
import com.oseong.ifeelalive.data.source.FavoritesPicRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providePicturesRepository(
        @AppModule.RemotePicturesDataSource picturesRemoteDataSource: PicturesDataSource
    ): AstroPicturesRepository {
        return AstroPicturesRepository(picturesRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideFavoritesPicRepository(
        @AppModule.LocalFavoritesPicDataSource favoritesPicDataSource: FavoritesPicDataSource
    ): FavoritesPicRepository {
        return FavoritesPicRepository(favoritesPicDataSource)
    }

}
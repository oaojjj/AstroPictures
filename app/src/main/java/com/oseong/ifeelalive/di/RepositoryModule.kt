package com.oseong.ifeelalive.di

import com.oseong.ifeelalive.data.source.PicturesDataSource
import com.oseong.ifeelalive.data.source.PicturesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providePicturesRepository(
        @AppModule.RemotePicturesDataSource picturesRemoteDataSource: PicturesDataSource
    ): PicturesDataSource {
        return PicturesRepository(picturesRemoteDataSource)
    }
}
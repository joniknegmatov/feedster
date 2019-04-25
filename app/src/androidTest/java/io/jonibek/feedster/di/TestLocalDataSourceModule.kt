package io.jonibek.feedster.di

import dagger.Module
import dagger.Provides
import io.jonibek.feedster.data.TestFavoritePostManager
import io.jonibek.feedster.data.datasource.post.local.PostLocalDataSource
import javax.inject.Singleton

@Module
class TestLocalDataSourceModule {

    @Singleton
    @Provides
    fun providesPostLocalDataSource(): PostLocalDataSource {
        return PostLocalDataSource.PostLocalDataSourceImpl(TestFavoritePostManager())
    }

}
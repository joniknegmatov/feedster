package io.jonibek.feedster.di.data

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import io.jonibek.feedster.data.datasource.post.local.FavoritePostManager
import io.jonibek.feedster.data.datasource.post.local.PostLocalDataSource
import javax.inject.Singleton

@Module
class LocalDataSourceModule {

    @Singleton
    @Provides
    fun providesPostLocalDataSource(sharedPreferences: SharedPreferences): PostLocalDataSource {
        return PostLocalDataSource.PostLocalDataSourceImpl(FavoritePostManager.FavoritePostManagerImpl(sharedPreferences))
    }

}
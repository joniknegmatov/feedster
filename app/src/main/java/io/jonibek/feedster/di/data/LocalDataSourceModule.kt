package io.jonibek.feedster.di.data

import dagger.Module
import dagger.Provides
import io.jonibek.feedster.data.datasource.post.local.FavoritePostManager
import io.jonibek.feedster.data.datasource.post.local.FavoritePostManagerImpl
import io.jonibek.feedster.data.datasource.post.local.PostLocalDataSource
import io.jonibek.feedster.data.datasource.post.local.PostLocalDataSourceImpl

@Module
class LocalDataSourceModule {

    @Provides
    fun provideFavoritePostManager(favoritePostManager: FavoritePostManagerImpl)
            : FavoritePostManager = favoritePostManager

    @Provides
    fun providesPostLocalDataSource(postLocalDataSource: PostLocalDataSourceImpl)
            : PostLocalDataSource = postLocalDataSource

}
package io.jonibek.feedster.di.data

import dagger.Module
import dagger.Provides
import io.jonibek.feedster.data.datasource.comment.remote.CommentApi
import io.jonibek.feedster.data.datasource.comment.remote.CommentRemoteDataSource
import io.jonibek.feedster.data.datasource.post.remote.PostApi
import io.jonibek.feedster.data.datasource.post.remote.PostRemoteDataSource
import io.jonibek.feedster.data.datasource.user.remote.UserApi
import io.jonibek.feedster.data.datasource.user.remote.UserRemoteDataSource
import retrofit2.Retrofit

@Module
class RemoteDataSourceModule {

    @Provides
    fun providesCommentRemoteDataSource(retrofit: Retrofit): CommentRemoteDataSource {
        return CommentRemoteDataSource.CommentRemoteDataSourceImpl(CommentApi.CommentApiImpl(retrofit))
    }

    @Provides
    fun providesPostRemoteDataSource(retrofit: Retrofit): PostRemoteDataSource {
        return PostRemoteDataSource.PostRemoteDataSourceImpl(PostApi.PostApiImpl(retrofit))
    }

    @Provides
    fun providesUserRemoteDataSource(retrofit: Retrofit): UserRemoteDataSource {
        return UserRemoteDataSource.UserRemoteDataSourceImpl(UserApi.UserApiImpl(retrofit))
    }
}
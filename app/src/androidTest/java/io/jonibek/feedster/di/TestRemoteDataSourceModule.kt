package io.jonibek.feedster.di

import dagger.Module
import dagger.Provides
import io.jonibek.feedster.data.datasource.comment.remote.CommentRemoteDataSource
import io.jonibek.feedster.data.datasource.post.remote.PostRemoteDataSource
import io.jonibek.feedster.data.datasource.user.remote.UserRemoteDataSource
import javax.inject.Singleton


@Module
class TestRemoteDataSourceModule(
    private val commentRemoteDataSource: CommentRemoteDataSource,
    private val postRemoteDataSource: PostRemoteDataSource,
    private val userRemoteDataSource: UserRemoteDataSource
) {

    @Provides
    @Singleton
    fun providesCommentRemoteDataSource(): CommentRemoteDataSource {
        return commentRemoteDataSource
    }

    @Provides
    @Singleton
    fun providesPostRemoteDataSource(): PostRemoteDataSource {
        return postRemoteDataSource
    }

    @Provides
    @Singleton
    fun providesUserRemoteDataSource(): UserRemoteDataSource {
        return userRemoteDataSource
    }

}
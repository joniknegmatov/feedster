package io.jonibek.feedster.di

import dagger.Module
import dagger.Provides
import io.jonibek.feedster.data.datasource.comment.remote.CommentRemoteDataSource
import io.jonibek.feedster.data.datasource.post.remote.PostRemoteDataSource
import io.jonibek.feedster.data.datasource.user.remote.UserRemoteDataSource
import org.mockito.Mockito
import javax.inject.Singleton


@Module
class TestRemoteDataSourceModule {

    companion object {
        val commentRemoteDataSource: CommentRemoteDataSource = Mockito.mock(CommentRemoteDataSource::class.java)
        val postRemoteDataSource: PostRemoteDataSource = Mockito.mock(PostRemoteDataSource::class.java)
        val userRemoteDataSource: UserRemoteDataSource = Mockito.mock(UserRemoteDataSource::class.java)
    }

    @Singleton
    @Provides
    fun providesCommentRemoteDataSource(): CommentRemoteDataSource {
        return commentRemoteDataSource
    }

    @Singleton
    @Provides
    fun providesPostRemoteDataSource(): PostRemoteDataSource {
        return postRemoteDataSource
    }

    @Singleton
    @Provides
    fun providesUserRemoteDataSource(): UserRemoteDataSource {
        return userRemoteDataSource
    }

}
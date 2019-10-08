package io.jonibek.feedster.di.data

import dagger.Module
import dagger.Provides
import io.jonibek.feedster.data.datasource.comment.remote.CommentRemoteDataSource
import io.jonibek.feedster.data.datasource.comment.remote.CommentRemoteDataSourceImpl
import io.jonibek.feedster.data.datasource.post.remote.PostRemoteDataSource
import io.jonibek.feedster.data.datasource.user.remote.UserRemoteDataSource
import io.jonibek.feedster.data.datasource.user.remote.UserRemoteDataSourceImpl
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RemoteDataSourceModule {

    @Singleton
    @Provides
    fun providesCommentRemoteDataSource(commentRemoteDataSource: CommentRemoteDataSourceImpl)
            : CommentRemoteDataSource = commentRemoteDataSource

    @Singleton
    @Provides
    fun providesPostRemoteDataSource(postRemoteDataSource: PostRemoteDataSource.PostRemoteDataSourceImpl)
            : PostRemoteDataSource = postRemoteDataSource

    @Singleton
    @Provides
    fun providesUserRemoteDataSource(userRemoteDataSource: UserRemoteDataSourceImpl)
            : UserRemoteDataSource = userRemoteDataSource

}
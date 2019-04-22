package io.jonibek.feedster.di.data

import dagger.Module
import dagger.Provides
import io.jonibek.feedster.data.datasource.comment.CommentRepository
import io.jonibek.feedster.data.datasource.comment.remote.CommentRemoteDataSource
import io.jonibek.feedster.data.datasource.post.PostRepository
import io.jonibek.feedster.data.datasource.post.remote.PostRemoteDataSource
import io.jonibek.feedster.data.datasource.user.UserRepository
import io.jonibek.feedster.data.datasource.user.remote.UserRemoteDataSource

@Module
class RepositoryModule {

    @Provides
    fun providesPostRepository(postRemoteDataSource: PostRemoteDataSource): PostRepository {
        return PostRepository.PostRepositoryImpl(postRemoteDataSource)
    }

    @Provides
    fun providesCommentRepository(commentRemoteDataSource: CommentRemoteDataSource): CommentRepository {
        return CommentRepository.CommentRepositoryImpl(commentRemoteDataSource)
    }

    @Provides
    fun providesUserRepository(userRemoteDataSource: UserRemoteDataSource): UserRepository {
        return UserRepository.UserRepositoryImpl(userRemoteDataSource)
    }

}
package io.jonibek.feedster.di.data

import dagger.Module
import dagger.Provides
import io.jonibek.feedster.data.repository.comment.CommentRepository
import io.jonibek.feedster.data.repository.comment.CommentRepositoryImpl
import io.jonibek.feedster.data.repository.post.PostRepository
import io.jonibek.feedster.data.repository.post.PostRepositoryImpl
import io.jonibek.feedster.data.repository.user.UserRepository
import io.jonibek.feedster.data.repository.user.UserRepositoryImpl

@Module
class RepositoryModule {

    @Provides
    fun providesPostRepository(postRepository: PostRepositoryImpl)
            : PostRepository = postRepository

    @Provides
    fun providesCommentRepository(commentRemoteDataSource: CommentRepositoryImpl)
            : CommentRepository = commentRemoteDataSource

    @Provides
    fun providesUserRepository(userRepository: UserRepositoryImpl)
            : UserRepository = userRepository

}
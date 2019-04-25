package io.jonibek.feedster.domain.post

import io.jonibek.feedster.data.datasource.comment.CommentRepository
import io.jonibek.feedster.data.datasource.post.PostRepository
import io.jonibek.feedster.data.datasource.user.UserRepository
import io.jonibek.feedster.data.entities.Comment
import io.jonibek.feedster.data.entities.Post
import io.jonibek.feedster.data.entities.User
import io.jonibek.feedster.domain.internal.BaseUseCase
import io.jonibek.feedster.domain.internal.BaseUseCaseInterface
import io.jonibek.feedster.domain.internal.UseCaseCallback
import io.reactivex.Scheduler
import javax.inject.Inject

interface PostUseCase : BaseUseCaseInterface {

    fun getPostById(postId: Int, callback: UseCaseCallback<Post>)

    fun getCommentsByPostId(postId: Int, callback: UseCaseCallback<List<Comment>>)

    fun getUserById(userId: Int, callback: UseCaseCallback<User>)

    class PostUseCaseImpl @Inject constructor(
        private val postRepository: PostRepository,
        private val commentRepository: CommentRepository,
        private val userRepository: UserRepository,
        subscribeScheduler: Scheduler,
        observeScheduler: Scheduler
    ) : PostUseCase, BaseUseCase(subscribeScheduler, observeScheduler) {

        override fun getUserById(userId: Int, callback: UseCaseCallback<User>) {
            addDisposable(userRepository.getUserById(userId), callback)
        }

        override fun getPostById(postId: Int, callback: UseCaseCallback<Post>) {
            addDisposable(postRepository.getPostById(postId), callback)
        }

        override fun getCommentsByPostId(postId: Int, callback: UseCaseCallback<List<Comment>>) {
            addDisposable(commentRepository.getCommentsByPostId(postId), callback)
        }


    }
}
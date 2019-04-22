package io.jonibek.feedster.domain.post

import io.jonibek.feedster.data.datasource.comment.CommentRepository
import io.jonibek.feedster.data.datasource.post.PostRepository
import io.jonibek.feedster.data.datasource.user.UserRepository
import io.jonibek.feedster.data.pojo.Comment
import io.jonibek.feedster.data.pojo.Post
import io.jonibek.feedster.data.pojo.User
import io.jonibek.feedster.domain.BaseUseCase
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

abstract class PostUseCase : BaseUseCase() {

    abstract fun getPostById(postId: Int, callback: Callback<Post>)

    abstract fun getCommentsByPostId(postId: Int, callback: Callback<List<Comment>>)

    abstract fun getUserById(userId: Int, callback: Callback<User>)

    class PostUseCaseImpl @Inject constructor(
        private val postRepository: PostRepository,
        private val commentRepository: CommentRepository,
        private val userRepository: UserRepository,
        private val subscribeScheduler: Scheduler,
        private val observeScheduler: Scheduler
    ) : PostUseCase() {


        override fun getUserById(userId: Int, callback: Callback<User>) {
            compositeDisposable.addAll(
                userRepository.getUserById(userId)
                    .subscribeOn(subscribeScheduler)
                    .observeOn(observeScheduler)
                    .subscribe({ callback.onResult(it) }, { callback.onFailure(it) })

            )
        }

        override fun getPostById(postId: Int, callback: Callback<Post>) {
            compositeDisposable.addAll(
                postRepository.getPostById(postId)
                    .subscribeOn(subscribeScheduler)
                    .observeOn(observeScheduler)
                    .subscribe({ callback.onResult(it) }, { callback.onFailure(it) })

            )
        }

        override fun getCommentsByPostId(postId: Int, callback: Callback<List<Comment>>) {
            compositeDisposable.addAll(
                commentRepository.getCommentsByPostId(postId)
                    .subscribeOn(subscribeScheduler)
                    .observeOn(observeScheduler)
                    .subscribe({ callback.onResult(it) }, { callback.onFailure(it) })

            )
        }


    }
}
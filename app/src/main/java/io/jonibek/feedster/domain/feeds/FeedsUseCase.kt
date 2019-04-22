package io.jonibek.feedster.domain.feeds

import io.jonibek.feedster.data.datasource.post.PostRepository
import io.jonibek.feedster.data.entities.Post
import io.jonibek.feedster.domain.BaseUseCase
import io.jonibek.feedster.domain.BaseUseCaseInterface
import io.jonibek.feedster.domain.UseCaseCallback
import io.reactivex.Scheduler
import javax.inject.Inject

interface FeedsUseCase : BaseUseCaseInterface {

    fun getAllPosts(callback: UseCaseCallback<List<Post>>)

    class FeedsUseCaseImpl @Inject constructor(
        private val postRepository: PostRepository,
        subscribeScheduler: Scheduler,
        observeScheduler: Scheduler
    ) : FeedsUseCase, BaseUseCase(subscribeScheduler, observeScheduler) {

        override fun getAllPosts(callback: UseCaseCallback<List<Post>>) {
            addDisposable(postRepository.getAllPosts(), callback)
        }

    }
}
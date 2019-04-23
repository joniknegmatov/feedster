package io.jonibek.feedster.domain.feeditem

import io.jonibek.feedster.data.datasource.post.PostRepository
import io.jonibek.feedster.domain.BaseUseCase
import io.jonibek.feedster.domain.BaseUseCaseInterface
import io.jonibek.feedster.domain.UseCaseCallback
import io.reactivex.Scheduler
import javax.inject.Inject

interface FeedItemUseCase : BaseUseCaseInterface {

    fun changeFavorite(postId: Int, callback: UseCaseCallback<Boolean>)

    fun isPostFavorite(postId: Int, callback: UseCaseCallback<Boolean>)

    class FeedItemUseCaseImpl @Inject constructor(
        private val postRepository: PostRepository,
        subscribeScheduler: Scheduler,
        observeScheduler: Scheduler
    ) : FeedItemUseCase, BaseUseCase(subscribeScheduler, observeScheduler) {

        override fun isPostFavorite(postId: Int, callback: UseCaseCallback<Boolean>) {
            addDisposable(postRepository.isPostInFavorites(postId), callback)
        }

        override fun changeFavorite(postId: Int, callback: UseCaseCallback<Boolean>) {
            addDisposable(postRepository.changeFavorite(postId), callback)
        }

    }
}
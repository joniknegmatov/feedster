package io.jonibek.feedster.domain.feeds

import io.jonibek.feedster.data.datasource.post.PostRepository
import io.jonibek.feedster.data.pojo.Post
import io.jonibek.feedster.domain.BaseUseCase
import io.reactivex.Scheduler
import javax.inject.Inject

abstract class FeedsUseCase : BaseUseCase() {

    abstract fun getAllPosts(callback: Callback<List<Post>?>)

    class FeedsUseCaseImpl @Inject constructor(
        private val postRepository: PostRepository,
        private val subscribeScheduler: Scheduler,
        private val observeScheduler: Scheduler
    ) : FeedsUseCase() {

        override fun getAllPosts(callback: Callback<List<Post>?>) {
            compositeDisposable.addAll(
                postRepository.getAllPosts()
                    .subscribeOn(subscribeScheduler)
                    .observeOn(observeScheduler)
                    .subscribe({ callback.onResult(it) }, { callback.onFailure(it) })
            )
        }

    }
}
package io.jonibek.feedster.di.features

import dagger.Module
import dagger.Provides
import io.jonibek.feedster.data.repository.post.PostRepository
import io.jonibek.feedster.domain.feeditem.FeedItemUseCase
import io.jonibek.feedster.domain.feeditem.FeedItemUseCaseImpl
import io.jonibek.feedster.domain.feeds.FeedsUseCase
import io.jonibek.feedster.domain.feeds.FeedsUseCaseImpl
import io.jonibek.feedster.presentation.feed.FeedsFragmentViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class FeedsFragmentModule {

    @Provides
    fun providesFeedsUseCase(feedsUseCaseImpl: FeedsUseCaseImpl)
            : FeedsUseCase = feedsUseCaseImpl

    @Provides
    fun providesFeedItemUseCase(feedItemUseCaseImpl: FeedItemUseCaseImpl)
            : FeedItemUseCase = feedItemUseCaseImpl

}
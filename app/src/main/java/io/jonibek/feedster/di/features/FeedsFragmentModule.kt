package io.jonibek.feedster.di.features

import dagger.Module
import dagger.Provides
import io.jonibek.feedster.data.datasource.post.PostRepository
import io.jonibek.feedster.domain.feeditem.FeedItemUseCase
import io.jonibek.feedster.domain.feeds.FeedsUseCase
import io.jonibek.feedster.ui.feed.FeedsFragmentViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class FeedsFragmentModule {

    @Provides
    fun providesFeedsUseCase(postRepository: PostRepository): FeedsUseCase {
        return FeedsUseCase.FeedsUseCaseImpl(postRepository, Schedulers.io(), AndroidSchedulers.mainThread())
    }

    @Provides
    fun providesFeedItemUseCase(postRepository: PostRepository): FeedItemUseCase {
        return FeedItemUseCase.FeedItemUseCaseImpl(postRepository, AndroidSchedulers.mainThread(), AndroidSchedulers.mainThread())
    }

    @Provides
    fun providesFeedsViewModelFactory(feedsUseCase: FeedsUseCase): FeedsFragmentViewModel.Factory {
        return FeedsFragmentViewModel.Factory(feedsUseCase)
    }


}
package io.jonibek.feedster.di.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.icu.text.DateTimePatternGenerator
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.jonibek.feedster.presentation.feed.FeedsFragmentViewModel
import io.jonibek.feedster.presentation.feed.feeditem.FeedItemViewModel
import io.jonibek.feedster.presentation.post.PostFragmentViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PostFragmentViewModel::class)
    internal abstract fun bindPostFragmentViewModel(postFragmentViewModel: PostFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FeedsFragmentViewModel::class)
    internal abstract fun bindFeedsFragmentViewModel(feedsFragmentViewModel: FeedsFragmentViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory

}
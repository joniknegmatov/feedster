package io.jonibek.feedster.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.jonibek.feedster.di.features.FeedsFragmentModule
import io.jonibek.feedster.di.features.PostFragmentModule
import io.jonibek.feedster.presentation.feed.FeedsFragment
import io.jonibek.feedster.presentation.post.PostFragment

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector(modules = [FeedsFragmentModule::class])
    internal abstract fun contributeFeedsFragment(): FeedsFragment

    @ContributesAndroidInjector(modules = [PostFragmentModule::class])
    internal abstract fun constributePostFragment() : PostFragment
}
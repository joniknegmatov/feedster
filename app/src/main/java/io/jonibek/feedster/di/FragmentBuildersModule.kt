package io.jonibek.feedster.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.jonibek.feedster.di.features.FeedsFragmentModule
import io.jonibek.feedster.di.features.PostFragmentModule
import io.jonibek.feedster.ui.MainActivity
import io.jonibek.feedster.ui.feed.FeedsFragment
import io.jonibek.feedster.ui.post.PostFragment

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [FeedsFragmentModule::class])
    internal abstract fun contributeFeedsFragment(): FeedsFragment

    @ContributesAndroidInjector(modules = [PostFragmentModule::class])
    internal abstract fun constributePostFragment() : PostFragment
}
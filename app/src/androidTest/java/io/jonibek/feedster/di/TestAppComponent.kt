package io.jonibek.feedster.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.jonibek.feedster.di.data.RepositoryModule
import io.jonibek.feedster.di.features.FeedsFragmentModule
import io.jonibek.feedster.di.features.PostFragmentModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        TestRemoteDataSourceModule::class,
        RepositoryModule::class,
        FragmentBuildersModule::class,
        FeedsFragmentModule::class,
        PostFragmentModule::class]
)
interface TestAppComponent : AndroidInjector<FeedsterTestApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<FeedsterTestApp>() {

        abstract fun setTestRemoteDataSourceModule(module: TestRemoteDataSourceModule): Builder

    }
}
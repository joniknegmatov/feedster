package io.jonibek.feedster.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import io.jonibek.feedster.di.data.RepositoryModule
import io.jonibek.feedster.di.features.FeedsFragmentModule
import io.jonibek.feedster.di.features.PostFragmentModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        TestRemoteDataSourceModule::class,
        TestLocalDataSourceModule::class,
        RepositoryModule::class,
        FragmentBuildersModule::class,
        FeedsFragmentModule::class,
        PostFragmentModule::class]
)
interface TestAppComponent : AndroidInjector<DaggerApplication> {


    fun inject(app: FeedsterTestApp)

    override fun inject(app: DaggerApplication)

    @Component.Builder
    interface Builder {

        fun setTestRemoteDataSourceModule(module: TestRemoteDataSourceModule): Builder

        fun build() : TestAppComponent
    }
}
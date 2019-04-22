package io.jonibek.feedster.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import io.jonibek.feedster.di.data.RepositoryModule
import io.jonibek.feedster.di.features.FeedsFragmentModule
import io.jonibek.feedster.di.features.PostFragmentModule
import io.jonibek.feedster.view.UiTest
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class,
        TestRemoteDataSourceModule::class,
        RepositoryModule::class,
        FragmentBuildersModule::class,
        FeedsFragmentModule::class,
        PostFragmentModule::class,
        NavigationModule::class]
)
interface TestAppComponent : AndroidInjector<DaggerApplication> {

    fun inject(app: FeedsterTestApp)

    fun inject(app: UiTest)

    override fun inject(app: DaggerApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun setAppModule(appModule: AppModule): Builder

        fun build(): TestAppComponent
    }
}
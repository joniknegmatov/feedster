package io.jonibek.feedster.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import io.jonibek.feedster.FeedsterApp
import io.jonibek.feedster.di.data.RemoteDataSourceModule
import io.jonibek.feedster.di.data.RepositoryModule
import io.jonibek.feedster.di.features.FeedsFragmentModule
import io.jonibek.feedster.di.features.PostFragmentModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class,
        RemoteDataSourceModule::class,
        RepositoryModule::class,
        FragmentBuildersModule::class,
        FeedsFragmentModule::class,
        PostFragmentModule::class,
        NavigationModule::class]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(app: FeedsterApp)

    override fun inject(app: DaggerApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun setAppModule(appModule: AppModule): Builder

        fun build(): AppComponent
    }
}
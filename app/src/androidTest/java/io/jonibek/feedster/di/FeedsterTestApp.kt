package io.jonibek.feedster.di

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.jonibek.feedster.FeedsterApp

class FeedsterTestApp : FeedsterApp() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerTestAppComponent
            .builder()
            .application(this)
            .setAppModule(AppModule(this))
            .build()

        appComponent.inject(this)
        return appComponent
    }
}
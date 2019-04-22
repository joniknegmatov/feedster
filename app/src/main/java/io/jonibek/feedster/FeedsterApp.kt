package io.jonibek.feedster

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.jonibek.feedster.di.AppModule
import io.jonibek.feedster.di.DaggerAppComponent

open class FeedsterApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .setAppModule(AppModule(this))
            .build()

        appComponent.inject(this)
        return appComponent
    }

}
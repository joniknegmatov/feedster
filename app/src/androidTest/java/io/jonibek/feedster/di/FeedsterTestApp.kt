package io.jonibek.feedster.di

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class FeedsterTestApp : Application(), HasSupportFragmentInjector,HasActivityInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var activityInjector : DispatchingAndroidInjector<Activity>

    override fun supportFragmentInjector() = fragmentInjector

    override fun activityInjector() = activityInjector


}
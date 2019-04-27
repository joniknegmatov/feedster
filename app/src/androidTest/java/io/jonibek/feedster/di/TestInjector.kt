package io.jonibek.feedster.di

import android.support.test.InstrumentationRegistry

class TestInjector(private val testRemoteDataSourceModule: TestRemoteDataSourceModule) {

    fun inject() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val app = instrumentation.targetContext.applicationContext as FeedsterTestApp

        DaggerTestAppComponent
                .builder()
                .setTestRemoteDataSourceModule(testRemoteDataSourceModule)
                .build()
                .inject(app)
    }
}
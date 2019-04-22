package io.jonibek.feedster.di

import android.app.Application
import android.content.Context
import android.support.test.runner.AndroidJUnitRunner

class TestApplicationRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        val testApplicationClassName = FeedsterTestApp::class.java.canonicalName
        return super.newApplication(cl, testApplicationClassName, context)
    }

}
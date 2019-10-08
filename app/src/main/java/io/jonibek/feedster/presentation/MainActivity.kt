package io.jonibek.feedster.presentation

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import io.jonibek.feedster.R

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}

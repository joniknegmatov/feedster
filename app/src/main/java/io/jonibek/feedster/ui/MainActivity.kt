package io.jonibek.feedster.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import dagger.android.support.DaggerAppCompatActivity
import io.jonibek.feedster.R
import io.jonibek.feedster.ui.feed.FeedsFragment

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}

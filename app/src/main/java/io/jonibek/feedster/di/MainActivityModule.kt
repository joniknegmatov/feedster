package io.jonibek.feedster.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.jonibek.feedster.presentation.MainActivity

@Suppress("unused")
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}
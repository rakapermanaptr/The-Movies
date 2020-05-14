package com.example.themovies.di.module

import com.example.themovies.presentation.discover.DiscoverFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModules {

    @ContributesAndroidInjector
    abstract fun contributeDiscoverFragment(): DiscoverFragment
}
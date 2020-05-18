package com.example.themovies.di.module

import com.example.themovies.presentation.detail.DetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeDetailActivity(): DetailActivity
}
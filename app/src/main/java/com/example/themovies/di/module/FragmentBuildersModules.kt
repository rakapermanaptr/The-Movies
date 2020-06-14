package com.example.themovies.di.module

import com.example.themovies.presentation.discover.DiscoverFragment
import com.example.themovies.presentation.nowplaying.NowPlayingFragment
import com.example.themovies.presentation.profile.ProfileFragment
import com.example.themovies.presentation.profile.favorite.FavoriteFragment
import com.example.themovies.presentation.upcoming.UpcomingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModules {

    @ContributesAndroidInjector
    abstract fun contributeDiscoverFragment(): DiscoverFragment

    @ContributesAndroidInjector
    abstract fun contributeNowPlayingFragment(): NowPlayingFragment

    @ContributesAndroidInjector
    abstract fun contributeUpcomingFragment(): UpcomingFragment

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributeFavoriteFragment(): FavoriteFragment
}
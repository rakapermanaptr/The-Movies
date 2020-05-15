package com.example.themovies.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.themovies.di.ViewModelFactory
import com.example.themovies.di.ViewModelKey
import com.example.themovies.presentation.discover.DiscoverViewModel
import com.example.themovies.presentation.nowplaying.NowPlayingViewModel
import com.example.themovies.presentation.upcoming.UpcomingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModules {

    @Binds
    @IntoMap
    @ViewModelKey(UpcomingViewModel::class)
    abstract fun bindUpcomingViewModel(upcomingViewModel: UpcomingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NowPlayingViewModel::class)
    abstract fun bindNowPlayingViewModel(nowPlayingViewModel: NowPlayingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DiscoverViewModel::class)
    abstract fun bindDiscoverViewModel(discoverViewModel: DiscoverViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
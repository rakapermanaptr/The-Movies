package com.example.themovies.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.themovies.di.ViewModelFactory
import com.example.themovies.di.ViewModelKey
import com.example.themovies.presentation.MainViewModel
import com.example.themovies.presentation.detail.DetailViewModel
import com.example.themovies.presentation.discover.DiscoverViewModel
import com.example.themovies.presentation.login.LoginViewModel
import com.example.themovies.presentation.nowplaying.NowPlayingViewModel
import com.example.themovies.presentation.profile.ProfileViewModel
import com.example.themovies.presentation.upcoming.UpcomingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModules {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(profileViewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(detailViewModel: DetailViewModel): ViewModel

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
package com.example.themovies.di

import com.example.themovies.TheMoviesApplication
import com.example.themovies.di.module.AppModule
import com.example.themovies.di.module.FragmentBuildersModules
import com.example.themovies.di.module.ViewModelModules
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ViewModelModules::class,
        FragmentBuildersModules::class
    ]
)
interface AppComponent : AndroidInjector<TheMoviesApplication>
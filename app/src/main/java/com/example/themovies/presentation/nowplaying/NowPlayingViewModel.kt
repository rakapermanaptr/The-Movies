package com.example.themovies.presentation.nowplaying

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.themovies.data.TheMoviesRepository
import com.example.themovies.domain.entities.Movie
import com.example.themovies.utils.vo.Resource
import javax.inject.Inject

class NowPlayingViewModel @Inject constructor(private val repository: TheMoviesRepository) : ViewModel() {

    fun getNowPlaying(): LiveData<Resource<List<Movie>>> = repository.getNowPlayingMovies()
}
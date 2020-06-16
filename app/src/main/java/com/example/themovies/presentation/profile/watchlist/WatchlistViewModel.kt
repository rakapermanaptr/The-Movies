package com.example.themovies.presentation.profile.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.themovies.data.TheMoviesRepository
import com.example.themovies.domain.entities.Movie
import com.example.themovies.utils.vo.Resource
import javax.inject.Inject

class WatchlistViewModel @Inject constructor(private val repository: TheMoviesRepository) : ViewModel() {

    fun getWatchlistMovies(sessionId: String): LiveData<Resource<List<Movie>>> =
        repository.getWatchlistMovies(sessionId)
}
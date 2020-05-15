package com.example.themovies.presentation.upcoming

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.themovies.data.TheMoviesRepository
import com.example.themovies.domain.entities.Movie
import com.example.themovies.utils.vo.Resource
import javax.inject.Inject

class UpcomingViewModel @Inject constructor(private val repository: TheMoviesRepository) :
    ViewModel() {

    fun getUpcoming(): LiveData<Resource<List<Movie>>> = repository.getUpcomingMovies()
}
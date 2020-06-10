package com.example.themovies.presentation.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.themovies.data.TheMoviesRepository
import com.example.themovies.domain.entities.Movie
import com.example.themovies.domain.entities.TvShow
import com.example.themovies.utils.vo.Resource
import javax.inject.Inject

class DiscoverViewModel @Inject constructor(private val repository: TheMoviesRepository) :
    ViewModel() {

    fun getPopularMovies(): LiveData<Resource<List<Movie>>> = repository.getPopularMovies()

    fun getPopularThriller(): LiveData<Resource<List<Movie>>> = repository.getMostPopularThriller()

    fun getPopularDrama(): LiveData<Resource<List<TvShow>>> = repository.getPopularDramaTvShows()

    fun getPopularAction(): LiveData<Resource<List<Movie>>> = repository.getPopularActionMovies()

}
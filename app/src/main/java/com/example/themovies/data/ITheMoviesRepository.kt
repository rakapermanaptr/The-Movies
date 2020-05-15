package com.example.themovies.data

import androidx.lifecycle.LiveData
import com.example.themovies.domain.entities.Movie
import com.example.themovies.domain.entities.TvShow
import com.example.themovies.utils.vo.Resource

interface ITheMoviesRepository {

    fun getPopularMovies(): LiveData<Resource<List<Movie>>>

    fun getPopularTvShows(): LiveData<Resource<List<TvShow>>>

    fun getPopularDramaTvShows(): LiveData<Resource<List<TvShow>>>

    fun getPopularActionMovies(): LiveData<Resource<List<Movie>>>

    fun getNowPlayingMovies(): LiveData<Resource<List<Movie>>>

    fun getUpcomingMovies(): LiveData<Resource<List<Movie>>>

}
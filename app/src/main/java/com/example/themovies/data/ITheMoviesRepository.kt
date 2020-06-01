package com.example.themovies.data

import androidx.lifecycle.LiveData
import com.example.themovies.domain.entities.*
import com.example.themovies.utils.vo.Resource

interface ITheMoviesRepository {

    fun getPopularMovies(): LiveData<Resource<List<Movie>>>

    fun getPopularTvShows(): LiveData<Resource<List<TvShow>>>

    fun getPopularDramaTvShows(): LiveData<Resource<List<TvShow>>>

    fun getPopularActionMovies(): LiveData<Resource<List<Movie>>>

    fun getNowPlayingMovies(): LiveData<Resource<List<Movie>>>

    fun getUpcomingMovies(): LiveData<Resource<List<Movie>>>

    fun getTvShowDetail(tvShowId: Int): LiveData<Resource<TvShowDetail>>

    fun getMovieDetail(movieId: Int): LiveData<Resource<MovieDetail>>

    fun getTvShowCaster(tvShowId: Int): LiveData<Resource<List<Cast>>>

    fun getMovieCaster(movieId: Int): LiveData<Resource<List<Cast>>>

    fun getSimilarTvShows(tvShowId: Int): LiveData<Resource<List<TvShow>>>

    fun getSimilarMovies(movieId: Int): LiveData<Resource<List<Movie>>>

    fun getRequestToken(): LiveData<Resource<RequestToken>>

    fun validateTokenWithLogin(dataLogin: ValidateWithLogin): LiveData<Resource<RequestToken>>

    fun createSession(createSession: CreateSession): LiveData<Resource<Session>>

}
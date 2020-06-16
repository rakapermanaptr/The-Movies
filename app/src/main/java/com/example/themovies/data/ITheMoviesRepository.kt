package com.example.themovies.data

import androidx.lifecycle.LiveData
import com.example.themovies.domain.entities.*
import com.example.themovies.utils.vo.Resource

interface ITheMoviesRepository {

    fun getPopularMovies(): LiveData<Resource<List<Movie>>>

    fun getMostPopularThriller(): LiveData<Resource<List<Movie>>>

    fun getPopularDrama(): LiveData<Resource<List<Movie>>>

    fun getPopularActionMovies(): LiveData<Resource<List<Movie>>>

    fun getNowPlayingMovies(): LiveData<Resource<List<Movie>>>

    fun getUpcomingMovies(): LiveData<Resource<List<Movie>>>

    fun getMovieDetail(movieId: Int): LiveData<Resource<MovieDetail>>

    fun getMovieCaster(movieId: Int): LiveData<Resource<List<Cast>>>

    fun getSimilarMovies(movieId: Int): LiveData<Resource<List<Movie>>>

    fun getRequestToken(): LiveData<Resource<RequestToken>>

    fun validateTokenWithLogin(dataLogin: ValidateWithLogin): LiveData<Resource<RequestToken>>

    fun createSession(createSession: CreateSession): LiveData<Resource<Session>>

    fun getProfileDetail(sessionId: String): LiveData<Resource<Profile>>

    fun postFavoriteMovie(sessionId: String, favorite: Favorite): LiveData<Resource<FavoriteResponse>>

    fun getFavoriteMovies(sessionId: String): LiveData<Resource<List<Movie>>>

    fun getWatchlistMovies(sessionId: String): LiveData<Resource<List<Movie>>>

    fun getMovieState(movieId: Int, sessionId: String): LiveData<Resource<MovieStates>>
}
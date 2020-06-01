package com.example.themovies.data

import androidx.lifecycle.LiveData
import com.example.themovies.data.source.remote.RemoteDataSource
import com.example.themovies.domain.entities.*
import com.example.themovies.utils.vo.Resource
import javax.inject.Inject

class TheMoviesRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    ITheMoviesRepository {

    override fun getPopularMovies(): LiveData<Resource<List<Movie>>> =
        remoteDataSource.getPopularMovies()

    override fun getPopularTvShows(): LiveData<Resource<List<TvShow>>> =
        remoteDataSource.getPopularTvShows()

    override fun getPopularDramaTvShows(): LiveData<Resource<List<TvShow>>> =
        remoteDataSource.getPopularDramaTvShows()

    override fun getPopularActionMovies(): LiveData<Resource<List<Movie>>> =
        remoteDataSource.getPopularActionMovies()

    override fun getNowPlayingMovies(): LiveData<Resource<List<Movie>>> =
        remoteDataSource.getNowPlayingMovies()

    override fun getUpcomingMovies(): LiveData<Resource<List<Movie>>> =
        remoteDataSource.getUpcomingMovies()

    override fun getTvShowDetail(tvShowId: Int): LiveData<Resource<TvShowDetail>> =
        remoteDataSource.getTvShowDetail(tvShowId)

    override fun getMovieDetail(movieId: Int): LiveData<Resource<MovieDetail>> =
        remoteDataSource.getMovieDetail(movieId)

    override fun getTvShowCaster(tvShowId: Int): LiveData<Resource<List<Cast>>> =
        remoteDataSource.getTvShowCaster(tvShowId)

    override fun getMovieCaster(movieId: Int): LiveData<Resource<List<Cast>>> =
        remoteDataSource.getMovieCaster(movieId)

    override fun getSimilarTvShows(tvShowId: Int): LiveData<Resource<List<TvShow>>> =
        remoteDataSource.getSimilarTvShows(tvShowId)

    override fun getSimilarMovies(movieId: Int): LiveData<Resource<List<Movie>>> =
        remoteDataSource.getSimilarMovies(movieId)

    override fun getRequestToken(): LiveData<Resource<RequestToken>> =
        remoteDataSource.getRequestToken()

    override fun validateTokenWithLogin(dataLogin: ValidateWithLogin): LiveData<Resource<RequestToken>> =
        remoteDataSource.validateTokenWithLogin(dataLogin)

    override fun createSession(createSession: CreateSession): LiveData<Resource<Session>> =
        remoteDataSource.createSession(createSession)

}
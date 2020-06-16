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

    override fun getMostPopularThriller(): LiveData<Resource<List<Movie>>> =
        remoteDataSource.getMostPopularThriller()

    override fun getPopularDrama(): LiveData<Resource<List<Movie>>> =
        remoteDataSource.getPopularDrama()

    override fun getPopularActionMovies(): LiveData<Resource<List<Movie>>> =
        remoteDataSource.getPopularActionMovies()

    override fun getNowPlayingMovies(): LiveData<Resource<List<Movie>>> =
        remoteDataSource.getNowPlayingMovies()

    override fun getUpcomingMovies(): LiveData<Resource<List<Movie>>> =
        remoteDataSource.getUpcomingMovies()

    override fun getMovieDetail(movieId: Int): LiveData<Resource<MovieDetail>> =
        remoteDataSource.getMovieDetail(movieId)

    override fun getMovieCaster(movieId: Int): LiveData<Resource<List<Cast>>> =
        remoteDataSource.getMovieCaster(movieId)

    override fun getSimilarMovies(movieId: Int): LiveData<Resource<List<Movie>>> =
        remoteDataSource.getSimilarMovies(movieId)

    override fun getRequestToken(): LiveData<Resource<RequestToken>> =
        remoteDataSource.getRequestToken()

    override fun validateTokenWithLogin(dataLogin: ValidateWithLogin): LiveData<Resource<RequestToken>> =
        remoteDataSource.validateTokenWithLogin(dataLogin)

    override fun createSession(createSession: CreateSession): LiveData<Resource<Session>> =
        remoteDataSource.createSession(createSession)

    override fun getProfileDetail(sessionId: String): LiveData<Resource<Profile>> =
        remoteDataSource.getProfileDetail(sessionId)

    override fun postFavoriteMovie(
        sessionId: String,
        favorite: Favorite
    ): LiveData<Resource<FavoriteResponse>> = remoteDataSource.postFavoriteMovie(sessionId, favorite)

    override fun getFavoriteMovies(sessionId: String): LiveData<Resource<List<Movie>>> =
        remoteDataSource.getFavoriteMovies(sessionId)

    override fun getWatchlistMovies(sessionId: String): LiveData<Resource<List<Movie>>> =
        remoteDataSource.getWatchlistMovies(sessionId)

    override fun getMovieState(movieId: Int, sessionId: String): LiveData<Resource<MovieStates>> =
        remoteDataSource.getMovieStates(movieId, sessionId)


}
package com.example.themovies.data

import androidx.lifecycle.LiveData
import com.example.themovies.data.source.remote.RemoteDataSource
import com.example.themovies.domain.entities.Movie
import com.example.themovies.domain.entities.TvShow
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


}
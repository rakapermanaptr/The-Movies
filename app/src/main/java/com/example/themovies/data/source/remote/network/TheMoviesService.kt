package com.example.themovies.data.source.remote.network

import com.example.themovies.domain.entities.BaseResponse
import com.example.themovies.domain.entities.Movie
import com.example.themovies.domain.entities.TvShow
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMoviesService {

    @GET("discover/movie")
    fun getPopularMoviesAsync(@Query("sort_by") sortBy: String): Deferred<BaseResponse<Movie>>

    @GET("discover/tv")
    fun getPopularTvShowsAsync(@Query("sort_by") sortBy: String): Deferred<BaseResponse<TvShow>>

    @GET("discover/tv")
    fun getPopularDramaTvShowsAsync(
        @Query("with_genres") genreId: Int,
        @Query("primary_release_year") releaseYear: Int
    ): Deferred<BaseResponse<TvShow>>

    @GET("discover/movie")
    fun getPopularActionMoviesAsync(
        @Query("with_genres") genreId: Int,
        @Query("primary_release_year") releaseYear: Int
    ): Deferred<BaseResponse<Movie>>

    @GET("movie/now_playing")
    fun getNowPlayingMoviesAsync(): Deferred<BaseResponse<Movie>>

    @GET("movie/upcoming")
    fun getUpcomingMoviesAsync(): Deferred<BaseResponse<Movie>>
}
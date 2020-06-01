package com.example.themovies.data.source.remote.network

import com.example.themovies.domain.entities.*
import kotlinx.coroutines.Deferred
import retrofit2.http.*

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

    @GET("tv/{tv_id}")
    fun getTvShowDetailAsync(@Path("tv_id") tvShowId: Int): Deferred<TvShowDetail>

    @GET("movie/{movie_id}")
    fun getMovieDetailAsync(@Path("movie_id") movieId: Int): Deferred<MovieDetail>

    @GET("tv/{tv_id}/credits")
    fun getTvShowCasterAsync(@Path("tv_id") tvShowId: Int): Deferred<CasterResponse>

    @GET("movie/{movie_id}/credits")
    fun getMovieCasterAsync(@Path("movie_id") tvShowId: Int): Deferred<CasterResponse>

    @GET("tv/{tv_id}/similar")
    fun getSimilarTvShowsAsync(@Path("tv_id") tvShowId: Int): Deferred<BaseResponse<TvShow>>

    @GET("movie/{movie_id}/similar")
    fun getSimilarMoviesAsync(@Path("movie_id") tvShowId: Int): Deferred<BaseResponse<Movie>>

    @GET("authentication/token/new")
    fun getRequestTokenAsync(): Deferred<RequestToken>

    @POST("authentication/token/validate_with_login")
    fun validateTokenWithLoginAsync(@Body validateWithLogin: ValidateWithLogin): Deferred<RequestToken>

    @POST("authentication/session/new")
    fun createSessionAsync(@Body createSession: CreateSession): Deferred<Session>

    @GET("account")
    fun getProfileDetailAsync(@Query("session_id") sessionId: String): Deferred<Profile>
}
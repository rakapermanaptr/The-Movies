package com.example.themovies.data.source.remote.network

import com.example.themovies.domain.entities.*
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface TheMoviesService {

    @GET("discover/movie")
    fun getPopularMoviesAsync(@Query("sort_by") sortBy: String): Deferred<BaseResponse<Movie>>

    @GET("discover/movie")
    fun getMostPopularThrillerAsync(
        @Query("with_genres") genresId: Int,
        @Query("primary_release_year") releaseYear: Int
    ): Deferred<BaseResponse<Movie>>

    @GET("discover/movie")
    fun getPopularDramaAsync(
        @Query("with_genres") genreId: Int,
        @Query("primary_release_year") releaseYear: Int
    ): Deferred<BaseResponse<Movie>>

    @GET("discover/movie")
    fun getPopularActionMoviesAsync(
        @Query("with_genres") genreId: Int,
        @Query("primary_release_year") releaseYear: Int
    ): Deferred<BaseResponse<Movie>>

    @GET("movie/now_playing")
    fun getNowPlayingMoviesAsync(): Deferred<BaseResponse<Movie>>

    @GET("movie/upcoming")
    fun getUpcomingMoviesAsync(): Deferred<BaseResponse<Movie>>

    @GET("movie/{movie_id}")
    fun getMovieDetailAsync(@Path("movie_id") movieId: Int): Deferred<MovieDetail>

    @GET("movie/{movie_id}/credits")
    fun getMovieCasterAsync(@Path("movie_id") tvShowId: Int): Deferred<CasterResponse>

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

    @POST("account/{account_id}/favorite")
    fun postFavoriteAsync(
        @Query("session_id") sessionId: String,
        @Body favorite: Favorite
    ): Deferred<FavoriteResponse>

    @POST("account/{account_id}/watchlist")
    fun postWatchlistAsync(
        @Query("session_id") sessionId: String,
        @Body watchlist: Watchlist
    ): Deferred<WatchlistResponse>

    @GET("account/{account_id}/favorite/movies")
    fun getFavoriteMoviesAsync(@Query("session_id") session_id: String): Deferred<BaseResponse<Movie>>

    @GET("account/{account_id}/watchlist/movies")
    fun getWatchlistMoviesAsync(@Query("session_id") sessionId: String): Deferred<BaseResponse<Movie>>

    @GET("movie/{movie_id}/account_states")
    fun getMovieStatesAsync(
        @Path("movie_id") movieId: Int,
        @Query("session_id") sessionId: String
    ): Deferred<MovieStates>
}
package com.example.themovies.domain.entities


import com.squareup.moshi.Json

data class TvShowDetail(
    @Json(name = "backdrop_path")
    val backdropPath: String,
    @Json(name = "first_air_date")
    val firstAirDate: String,
    @Json(name = "homepage")
    val homepage: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "number_of_episodes")
    val numberOfEpisodes: Int,
    @Json(name = "original_name")
    val originalName: String,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "popularity")
    val popularity: Double,
    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "status")
    val status: String,
    @Json(name = "vote_average")
    val voteAverage: Double
)
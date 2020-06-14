package com.example.themovies.domain.entities


import com.squareup.moshi.Json

data class MovieStates(
    @Json(name = "favorite")
    val favorite: Boolean,
    @Json(name = "id")
    val id: Int,
    @Json(name = "rated")
    val rated: Boolean,
    @Json(name = "watchlist")
    val watchlist: Boolean
)
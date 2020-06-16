package com.example.themovies.domain.entities


import com.squareup.moshi.Json

data class Watchlist(
    @Json(name = "watchlist")
    val watchlist: Boolean,
    @Json(name = "media_id")
    val mediaId: Int,
    @Json(name = "media_type")
    val mediaType: String
)
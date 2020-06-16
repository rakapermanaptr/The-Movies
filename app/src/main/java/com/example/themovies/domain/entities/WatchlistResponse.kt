package com.example.themovies.domain.entities


import com.squareup.moshi.Json

data class WatchlistResponse(
    @Json(name = "status_code")
    val statusCode: Int,
    @Json(name = "status_message")
    val statusMessage: String
)
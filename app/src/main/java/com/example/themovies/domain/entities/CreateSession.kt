package com.example.themovies.domain.entities


import com.squareup.moshi.Json

data class CreateSession(
    @Json(name = "request_token")
    val requestToken: String
)
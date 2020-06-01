package com.example.themovies.domain.entities


import com.squareup.moshi.Json

data class ValidateWithLogin(
    @Json(name = "password")
    val password: String,
    @Json(name = "request_token")
    val requestToken: String,
    @Json(name = "username")
    val username: String
)
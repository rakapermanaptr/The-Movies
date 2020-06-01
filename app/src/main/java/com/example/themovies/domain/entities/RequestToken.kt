package com.example.themovies.domain.entities


import com.squareup.moshi.Json

data class RequestToken(
    @Json(name = "expires_at")
    val expiresAt: String,
    @Json(name = "request_token")
    val requestToken: String,
    @Json(name = "success")
    val success: Boolean
)
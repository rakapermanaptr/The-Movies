package com.example.themovies.domain.entities


import com.squareup.moshi.Json

data class Session(
    @Json(name = "session_id")
    val sessionId: String,
    @Json(name = "success")
    val success: Boolean
)
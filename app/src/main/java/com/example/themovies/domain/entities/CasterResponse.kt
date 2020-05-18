package com.example.themovies.domain.entities


import com.squareup.moshi.Json

data class CasterResponse(
    @Json(name = "cast")
    val cast: List<Cast>,
    @Json(name = "id")
    val id: Int
)
package com.example.themovies.domain.entities


import com.squareup.moshi.Json

data class Favorite(
    @Json(name = "favorite")
    val favorite: Boolean,
    @Json(name = "media_id")
    val mediaId: Int,
    @Json(name = "media_type")
    val mediaType: String
)
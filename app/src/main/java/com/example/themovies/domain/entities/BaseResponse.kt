package com.example.themovies.domain.entities


import com.squareup.moshi.Json

data class BaseResponse<T>(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val data: List<T>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)
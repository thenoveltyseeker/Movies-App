package com.accubits.test.moviesapp.model.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoviesWrapperResponse(
        @Json(name = "results")
        val movies: List<MovieResponse>
)

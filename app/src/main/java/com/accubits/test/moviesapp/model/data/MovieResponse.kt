package com.accubits.test.moviesapp.model.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @author Rolbin, created on 08-01-2021.
 */
@JsonClass(generateAdapter = true)
data class MovieResponse(
        @Json(name = "id")
        val id: String,
        @Json(name = "backdrop_path")
        val backdropPath: String?,
        @Json(name = "title")
        val title: String,
        @Json(name = "poster_path")
        val posterPath: String?,
        @Json(name = "release_date")
        val releaseDate: String,
        @Json(name = "popularity")
        val rating: Double
)

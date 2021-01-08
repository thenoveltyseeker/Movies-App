package com.accubits.test.moviesapp.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Rolbin, created on 08-01-2021.
 */
@Entity(tableName = "movie_table")
data class Movie(
        @PrimaryKey
        val id: String,
        val name: String,
        val thumbnail: String,
        val releaseDate: String,
        val isNewVideo: Boolean,
        val isBestRated: Boolean
)
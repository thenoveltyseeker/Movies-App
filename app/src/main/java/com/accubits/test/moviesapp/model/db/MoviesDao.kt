package com.accubits.test.moviesapp.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.accubits.test.moviesapp.model.data.Movie

/**
 *  @author Rolbin, created on 08-01-2021.
 */
@Dao
interface MoviesDao {

    //Returning List instead of Flow or LiveData since there is no reactive approach for the screen
    @Query("SELECT * FROM movie_table")
    suspend fun getAllMovies(): List<Movie>

    @Insert
    suspend fun insertMovies(movies: List<Movie>)
}
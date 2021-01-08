package com.accubits.test.moviesapp.model.repo

import com.accubits.test.moviesapp.model.data.Movie

/**
 * @author Rolbin, created on 08-01-2021.
 */
interface MoviesRepo {

    suspend fun getMovies(): List<Movie>

}
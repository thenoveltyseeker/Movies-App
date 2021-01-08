package com.accubits.test.moviesapp.model.repo

import com.accubits.test.moviesapp.model.data.Movie
import com.accubits.test.moviesapp.model.db.MoviesDao
import com.accubits.test.moviesapp.model.network.NetworkRequestFactory
import com.accubits.test.moviesapp.model.network.Result

/**
 * @author Rolbin, created on 08-01-2021.
 */
class MoviesRepoImpl(
        private val networkRequestFactory: NetworkRequestFactory,
        private val moviesApi: MoviesApi,
        private val moviesDao: MoviesDao
) : MoviesRepo {

    override suspend fun getMovies(): List<Movie> {
        val movieList = networkRequestFactory.performRequest {
            val moviesWrapper = moviesApi.getMovies()
            val moviesList = moviesWrapper.movies.map { movie ->
                with(movie) {
                    val thumbnail = when {
                        backdropPath != null -> BACK_DROP_BASE.plus(backdropPath)
                        posterPath != null -> POSTER_BASE.plus(posterPath)
                        else -> ""
                    }
                    val movieYear = releaseDate.split("-")[0]
                    //Converting to UI model
                    Movie(id, title, thumbnail, movieYear, backdropPath != null, rating > 5)
                }
            }
            moviesDao.insertMovies(moviesList)
            moviesList
        }

        //Due to time constraint and there is only 1 request per app launch, letting the request to fail inorder to fetch from DB,
        // instead of checking for internet connection
        return when (movieList) {
            is Result.Success -> {
                movieList.data
            }
            is Result.Failure -> {
                getLocalMovies()
            }
        }
    }

    private suspend fun getLocalMovies(): List<Movie> {
        return moviesDao.getAllMovies()
    }
}

private const val BACK_DROP_BASE = "http://image.tmdb.org/t/p/w780"
private const val POSTER_BASE = "http://image.tmdb.org/t/p/w185"
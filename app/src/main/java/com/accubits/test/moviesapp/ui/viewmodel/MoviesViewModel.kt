package com.accubits.test.moviesapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.accubits.test.moviesapp.model.repo.MoviesRepo

/**
 * @author Rolbin, created on 08-01-2021.
 */
class MoviesViewModel(private val moviesRepo: MoviesRepo) : ViewModel() {

    val movies = liveData {
        val movieList = moviesRepo.getMovies()
        emit(movieList)
    }

}

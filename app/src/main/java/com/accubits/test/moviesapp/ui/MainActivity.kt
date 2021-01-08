package com.accubits.test.moviesapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.accubits.test.moviesapp.R
import com.accubits.test.moviesapp.model.data.Movie
import com.accubits.test.moviesapp.ui.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity() {
    private val moviesViewModel by viewModel<MoviesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moviesViewModel.movies.observe(this) { movieList ->
            val moviesAdapter = getAdapter(movieList, R.layout.list_item_movie)
            setupRecyclerView(moviesAdapter, rvMovies)

            //New releases only
            val newRelease = getAdapter(movieList.filter { it.isNewVideo }, R.layout.list_item_movie_categories)
            setupRecyclerView(newRelease, rvNewReleaseMovies)

            //Best rated only
            val bestRatedAdapter = getAdapter(movieList.filter { it.isBestRated }, R.layout.list_item_movie_categories)
            setupRecyclerView(bestRatedAdapter, rvBestRatedMovies)
        }
    }

    private fun getAdapter(movieList: List<Movie>, @LayoutRes layout: Int): MoviesAdapter {
        val moviesAdapter by inject<MoviesAdapter> {
            parametersOf(movieList, layout)
        }
        return moviesAdapter
    }

    private fun setupRecyclerView(moviesAdapter: MoviesAdapter, recyclerView: RecyclerView) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
            adapter = moviesAdapter
        }
    }
}
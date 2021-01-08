package com.accubits.test.moviesapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.accubits.test.moviesapp.R
import com.accubits.test.moviesapp.model.data.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions

/**
 * @author Rolbin, created on 08-01-2021.
 */
class MoviesAdapter(
        private val movies: List<Movie>,
        @LayoutRes
        private val layout: Int
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount() = movies.size

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val movieImage = view.findViewById<ImageView>(R.id.ivMoviePoster)
        private val movieName = view.findViewById<TextView>(R.id.tvMovieName)
        private val releaseDate = view.findViewById<TextView>(R.id.tvReleaseDate)

        fun bind(movie: Movie) {
            movieImage.loadAsBitmap(movie.thumbnail)
            movieName.text = movie.name
            releaseDate.text = movie.releaseDate
        }
    }
}

fun ImageView.loadAsBitmap(url: String, errorDrawable: Int? = R.drawable.ic_placeholder, placeHolder: Int? = R.drawable.ic_placeholder) {
        val requestManager = Glide.with(context).asBitmap().load(url)
        requestManager.apply {
            errorDrawable?.let {
                this.error(it)
            }
            transition(BitmapTransitionOptions().crossFade(1000)).centerCrop()
        }.into(this)
}
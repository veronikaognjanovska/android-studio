package com.example.lab3.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lab3.R
import com.example.lab3.models.Movie

class MovieAdapter(val context: Context, var allMoviesList: MutableList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var onItemClick: ((Movie) -> Unit)? = null

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieTitle: TextView
        val movieYear: TextView
        val movieImage: ImageView
        val movieListItem: View

        init {
            movieTitle = view.findViewById(R.id.movieTitle)
            movieYear = view.findViewById(R.id.movieYear)
            movieImage = view.findViewById(R.id.movieImage)
            movieListItem = view.findViewById(R.id.movieListItem)
            movieListItem.setOnClickListener {
                onItemClick?.invoke(allMoviesList[absoluteAdapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // create view holder - from layout
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // bind item with view items
        val currentMovie = allMoviesList[position]
        holder.movieTitle.text = currentMovie.Title
        holder.movieYear.text = currentMovie.Year.toString()
        Glide.with(context.applicationContext).load(currentMovie.Poster).into(holder.movieImage)
    }

    override fun getItemCount(): Int {
        return allMoviesList.size
    }

    fun updateData(data: MutableList<Movie>) {
        this.allMoviesList = data
        this.notifyDataSetChanged()
    }

}
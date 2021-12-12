package com.example.lab2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab2.R
import com.example.lab2.models.Movie

class MovieAdapter(val context: Context, var allMovieList: MutableList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var onItemClick: ((Movie) -> Unit)? = null

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rowMovieId: TextView
        val rowMovieTitle: TextView
        val rowMovieDirectorName: TextView
        val rowMovieListItem: View

        init {
            rowMovieId = view.findViewById(R.id.rowMovieId)
            rowMovieTitle = view.findViewById(R.id.rowMovieTitle)
            rowMovieDirectorName = view.findViewById(R.id.rowMovieDirectorName)
            rowMovieListItem = view.findViewById(R.id.rowMovieListItem)
            rowMovieListItem.setOnClickListener {
                onItemClick?.invoke(allMovieList[absoluteAdapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_list_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMovie = allMovieList[position]
        holder.rowMovieId.text = currentMovie.id.toString()
        holder.rowMovieTitle.text = currentMovie.title
        holder.rowMovieDirectorName.text =
            currentMovie.director.name + ' ' + currentMovie.director.surname
    }

    override fun getItemCount(): Int {
        return allMovieList.size
    }

    fun updateData(data: MutableList<Movie>) {
        this.allMovieList = data
        this.notifyDataSetChanged()
    }

}
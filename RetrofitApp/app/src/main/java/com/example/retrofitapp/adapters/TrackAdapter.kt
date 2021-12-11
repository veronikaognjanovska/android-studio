package com.example.retrofitapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapp.R
import com.example.retrofitapp.models.Data

class TrackAdapter(val context: Context, var allTrackList: MutableList<Data>) :
    RecyclerView.Adapter<TrackAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val trackId: TextView
        val trackTitle: TextView

        init {
            trackId = view.findViewById(R.id.trackId)
            trackTitle = view.findViewById(R.id.trackTitle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // create view holder - from layout
        val view = LayoutInflater.from(parent.context).inflate(R.layout.track, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // bind item with view items
        val currentTrack = allTrackList[position]
        holder.trackId.text = currentTrack.id.toString()
        holder.trackTitle.text = currentTrack.title
    }

    override fun getItemCount(): Int {
        return allTrackList.size
    }

    fun updateData(data: MutableList<Data>) {
        this.allTrackList = data
        this.notifyDataSetChanged()
    }

}
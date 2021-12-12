package com.example.lab2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab2.R
import com.example.lab2.models.Actor

class ActorAdapter(val context: Context, var allActorList: MutableList<Actor>) :
    RecyclerView.Adapter<ActorAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rowActorId: TextView
        val rowActorName: TextView
        val rowActorSurname: TextView
        val rowActorYear: TextView

        init {
            rowActorId = view.findViewById(R.id.rowActorId)
            rowActorName = view.findViewById(R.id.rowActorName)
            rowActorSurname = view.findViewById(R.id.rowActorSurname)
            rowActorYear = view.findViewById(R.id.rowActorYear)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.actor_list_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentActor = allActorList[position]
        holder.rowActorId.text = context.getString(R.string.id_label, currentActor.id.toString())
        holder.rowActorName.text = currentActor.name
        holder.rowActorSurname.text = currentActor.surname
        holder.rowActorYear.text = currentActor.birthYear.toString()
    }

    override fun getItemCount(): Int {
        return allActorList.size
    }

    fun updateData(data: MutableList<Actor>) {
        this.allActorList = data
        this.notifyDataSetChanged()
    }

}
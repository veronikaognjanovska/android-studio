package com.example.firstapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.R
import com.example.firstapp.models.Student

class BasicRecyclerViewAdapter(val context: Context, val allStudents: MutableList<Student>) :
    RecyclerView.Adapter<BasicRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val index: TextView
        val name: TextView
        val surname: TextView

        init {
            index = view.findViewById(R.id.index)
            name = view.findViewById(R.id.firstName)
            surname = view.findViewById(R.id.surname)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_row, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentStudent:Student = allStudents[position]
        holder.index.text=currentStudent.index.toString()
        holder.name.text=currentStudent.name
        holder.surname.text=currentStudent.surname
    }

    override fun getItemCount(): Int {
        return allStudents.size
    }
}
package com.example.lab4.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4.R
import com.example.lab4.models.Student

class StudentAdapter(val context: Context, var allStudentsList: MutableList<Student>) :
    RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    var onDeleteClick: ((Student) -> Unit)? = null
    var onEditClick: ((Student) -> Unit)? = null

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rowStudentIndex: TextView
        val rowStudentFullName: TextView
        val rowButtonStudentEdit: Button
        val rowButtonStudentDelete: Button
        val studentListItem: View

        init {
            rowStudentIndex = view.findViewById(R.id.rowStudentIndex)
            rowStudentFullName = view.findViewById(R.id.rowStudentFullName)
            rowButtonStudentEdit = view.findViewById(R.id.rowButtonStudentEdit)
            rowButtonStudentDelete = view.findViewById(R.id.rowButtonStudentDelete)
            studentListItem = view.findViewById(R.id.studentListItem)

            rowButtonStudentEdit.setOnClickListener {
                onEditClick?.invoke(allStudentsList[adapterPosition])
            }
            rowButtonStudentDelete.setOnClickListener {
                onDeleteClick?.invoke(allStudentsList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // create view holder - from layout
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // bind item with view items
        val currentStudent = allStudentsList[position]
        holder.rowStudentIndex.text = currentStudent.index
        holder.rowStudentFullName.text = currentStudent.name + " " + currentStudent.surname
    }

    override fun getItemCount(): Int {
        return allStudentsList.size
    }

    fun updateData(data: MutableList<Student>) {
        this.allStudentsList = data
        this.notifyDataSetChanged()
    }

}
package com.example.firstapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.firstapp.R
import com.example.firstapp.models.Student

class BasicListViewAdapter(val context: Context, val allStudents: MutableList<Student>) :
    BaseAdapter() {

    private var layoutInflator: LayoutInflater

    init {
        layoutInflator = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return allStudents.size
    }

    override fun getItem(position: Int): Any {
        return allStudents[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View
        if (convertView == null) {
            view = layoutInflator.inflate(R.layout.list_view_row, null, false)
        } else {
            view = convertView
        }

        val currentStudent = allStudents[position]
        val index = view.findViewById<TextView>(R.id.index)
        val fullName = view.findViewById<TextView>(R.id.fullName)
        index.text = currentStudent.index.toString()
        fullName.text = currentStudent.name + " " + currentStudent.surname

        return view
    }
}
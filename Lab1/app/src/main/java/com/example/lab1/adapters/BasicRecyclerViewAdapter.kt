package com.example.lab1.adapters

import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab1.R

class BasicRecyclerViewAdapter(
    val context: Context,
    val items: List<ResolveInfo>,
    val packageManager: PackageManager?
) :
    RecyclerView.Adapter<BasicRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemText: TextView = view.findViewById(R.id.itemText)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_implicit_view_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem: ResolveInfo = items[position]
        holder.itemText.text =
            packageManager?.let {
                currentItem.activityInfo.applicationInfo.loadLabel(it).toString()
            }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
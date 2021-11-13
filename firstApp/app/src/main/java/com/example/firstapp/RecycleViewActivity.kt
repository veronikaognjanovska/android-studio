package com.example.firstapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.adapters.BasicRecyclerViewAdapter
import com.example.firstapp.models.Student

class RecycleViewActivity : AppCompatActivity() {
    // https://developer.android.com/jetpack/androidx/releases/recyclerview
    // dependencies
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_view)

        var data: MutableList<Student> = initList()

        var recyclerViewComponent = findViewById<RecyclerView>(R.id.recyclerViewComponent)
        recyclerViewComponent.setHasFixedSize(true)
        recyclerViewComponent.layoutManager = LinearLayoutManager(this)

        val recyclerViewAdapter = BasicRecyclerViewAdapter(this, data)
        recyclerViewComponent.adapter = recyclerViewAdapter


    }

    private fun initList(): MutableList<Student> {
        return mutableListOf(
            Student("Veronika", "Ognjanovska", 181045),
            Student("V", "O", 1810451),
            Student("Ve", "Og", 1810452),
            Student("Ver", "Ognj", 1810453),
        )
    }

}
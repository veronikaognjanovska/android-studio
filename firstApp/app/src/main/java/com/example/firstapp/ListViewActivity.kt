package com.example.firstapp

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapp.adapters.BasicListViewAdapter
import com.example.firstapp.models.Student

class ListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        val data: MutableList<Student> = initList()
        var listViewComponent = findViewById<ListView>(R.id.basicListView)

//        val data: MutableList<String> = initList()
//        val adapter: ArrayAdapter<String> =
//            ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
//        listViewComponent.adapter = adapter

        val basicAdapter = BasicListViewAdapter(this, data)
        listViewComponent.adapter = basicAdapter

    }

    private fun initList(): MutableList<Student> {
//        return mutableListOf(
//            "item 1", "item 2", "item 3", "item 4", "item 5",
//            "item 6", "item 7", "item 8", "item 89", "item 10",
//            "item 11", "item 12", "item 13", "item 14", "item 15"
//        )
        return mutableListOf(
            Student("Veronika", "Ognjanovska", 181045),
            Student("V", "O", 1810451),
            Student("Ve", "Og", 1810452),
            Student("Ver", "Ognj", 1810453),
        )
    }

}
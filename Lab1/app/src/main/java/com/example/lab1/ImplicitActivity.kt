package com.example.lab1

import android.content.Intent
import android.content.pm.ResolveInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab1.adapters.BasicRecyclerViewAdapter

class ImplicitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit)

        val packageManager = packageManager

        val data: List<ResolveInfo> = initList()

        var recyclerViewComponent = findViewById<RecyclerView>(R.id.recyclerViewComponent)
        recyclerViewComponent.setHasFixedSize(true)
        recyclerViewComponent.layoutManager = LinearLayoutManager(this)

        val recyclerViewAdapter = BasicRecyclerViewAdapter(this, data, packageManager)
        recyclerViewComponent.adapter = recyclerViewAdapter

    }

    private fun initList(): List<ResolveInfo> {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        return packageManager.queryIntentActivities(intent, 0)
    }
}
package com.example.firstapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapp.fragments.FirstFragment

class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        supportFragmentManager.beginTransaction()
            .add(R.id.container, FirstFragment())
            .commit()
    }
}
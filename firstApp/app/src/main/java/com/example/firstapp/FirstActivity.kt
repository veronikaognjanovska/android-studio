package com.example.firstapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        val bundle: Bundle? = intent.extras
        val userNameControl = findViewById<TextView>(R.id.username)

        if (bundle != null) {
            val userName = bundle.get("UserName")
            userNameControl.text = "Hello " + userName.toString()
        }

    }
}
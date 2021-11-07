package com.example.firstapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
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

        var backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            val backIntent = Intent(this, MainActivity::class.java)
            startActivity(backIntent)
        }

    }
}
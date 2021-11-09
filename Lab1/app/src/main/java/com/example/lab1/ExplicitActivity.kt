package com.example.lab1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ExplicitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit)
    }

    fun okay(view: View?) {
        val intent = Intent(this, MainActivity::class.java)
        val inputText = findViewById<EditText>(R.id.inputText).text.toString()
        intent.putExtra("inputText", inputText)
        startActivity(intent)
    }

    fun cancel(view: View?) {
        // back
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}
package com.example.firstapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // in onCreate we define all listeners
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.v("Main Activity", "onCreate")

        val firstButton = findViewById<Button>(R.id.firstButton)
        val implicitButton = findViewById<Button>(R.id.implicitButton)
        val nameControl = findViewById<EditText>(R.id.name)

        firstButton.setOnClickListener {
            val firstIntent = Intent(this, FirstActivity::class.java)
            val nameValue = nameControl.text.toString()
            firstIntent.putExtra("UserName", nameValue)
            startActivity(firstIntent)
        }

        implicitButton.setOnClickListener {
            val secondIntent = Intent(Intent.ACTION_VIEW)
            secondIntent.data = Uri.parse("https://www.youtube.com/watch?v=qU9mHegkTc4")
            startActivity(secondIntent)
        }

    }

    override fun onStart() {
        super.onStart()
        Log.v("Main Activity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.v("Main Activity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.v("Main Activity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.v("Main Activity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v("Main Activity", "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.v("Main Activity", "onRestart")
    }


}
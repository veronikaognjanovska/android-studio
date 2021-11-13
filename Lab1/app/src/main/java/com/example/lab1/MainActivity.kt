package com.example.lab1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.setBundle(intent.extras)
        this.setListeners()
    }

    private fun setBundle(bundle: Bundle?) {
        if (bundle != null) {
            var bundleText = bundle.getString("inputText")
            bundleText = if (bundleText == "") "Hello !" else bundleText
            val resultExplicitText = findViewById<TextView>(R.id.resultExplicitText)
            resultExplicitText.text = bundleText
        }
    }

    private fun setListeners() {
        val buttonExplicitActivity = findViewById<Button>(R.id.buttonExplicitActivity)
        buttonExplicitActivity.setOnClickListener {
            val intent = Intent(this, ExplicitActivity::class.java)
            startActivity(intent)
        }

        val buttonImplicitActivity = findViewById<Button>(R.id.buttonImplicitActivity)
        buttonImplicitActivity.setOnClickListener {
            val intent = Intent("mk.ukim.finki.mpip.IMPLICIT_ACTION")
            startActivity(intent)
        }

        val buttonSendActivity = findViewById<Button>(R.id.buttonSendActivity)
        buttonSendActivity.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_SUBJECT, "MPiP Send Title")
            intent.putExtra(Intent.EXTRA_TEXT, "Content send from MainActivity")
            startActivity(Intent.createChooser(intent, "Send !"))
        }

        val buttonSelectImageActivity = findViewById<Button>(R.id.buttonSelectImageActivity)
        buttonSelectImageActivity.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            getSelectedImageResult.launch(intent)
        }
    }

    private val getSelectedImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { it ->
            if (it.resultCode == Activity.RESULT_OK) {
                val selectedFileUri = it.data?.data
                if (selectedFileUri != null) {
                    // selectedFileUri now contains URI to selected image
                    try {
                        val bitmap =
                            MediaStore.Images.Media.getBitmap(contentResolver, selectedFileUri)
                        val imageView: ImageView = findViewById(R.id.imageView)
                        imageView.setImageBitmap(bitmap)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }
}
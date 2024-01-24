package com.madassignment.pama

// This is a splash screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView
import com.madassignment.pama.Activities.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val logoId: ImageView = findViewById(R.id.splashImageView)
        val textViewLogo: TextView = findViewById(R.id.splashTextView)

        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}

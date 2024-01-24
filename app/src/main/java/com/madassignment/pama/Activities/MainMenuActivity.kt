package com.madassignment.pama.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.madassignment.pama.R
import android.view.Menu
import android.view.MenuItem
import android.content.Intent
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.madassignment.pama.Dashboard.HelpActivity
import com.madassignment.pama.Dashboard.MyProfileActivity
import com.madassignment.pama.Dashboard.PhoneCallActivity
import com.madassignment.pama.Dashboard.SendEmailActivity
import com.madassignment.pama.Dashboard.SendMessageActivity
import com.madassignment.pama.Dashboard.SettingsActivity
import com.madassignment.pama.Dashboard.WebBrowserActivity
import com.google.firebase.auth.FirebaseAuth
import com.madassignment.pama.Dashboard.PlayMusicActivity

class MainMenuActivity : AppCompatActivity() {

    private lateinit var currentUserEmailTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        currentUserEmailTextView = findViewById(R.id.currentUserEmailTextView)

        val currentUser = FirebaseAuth.getInstance().currentUser
        currentUser?.let {
            currentUserEmailTextView.text = currentUser.email

        }
        // ImageView
        val phoneCallImageView: ImageView = findViewById(R.id.phoneCallImageView)
        val emailImageView: ImageView = findViewById(R.id.emailImageView)
        val webBrowserImageView: ImageView = findViewById(R.id.webBrowserImageView)
        val smsImageView: ImageView = findViewById(R.id.smsImageView)
        val musicImageView : ImageView = findViewById(R.id.musicImageView)

        // TextViews
        // val mainMenuTextView: TextView = findViewById(R.id.mainMenuTextView)
        val callTextView: TextView = findViewById(R.id.CallTextView)
        val emailTextView: TextView = findViewById(R.id.emailTextView)
        val smsTextView: TextView = findViewById(R.id.smsTextView)
        val musicTextView : TextView = findViewById(R.id.musicTextView)
        // Making phoneCall
        phoneCallImageView.setOnClickListener {
            Toast.makeText(this, "Make a phone call", Toast.LENGTH_SHORT).show()
            val call = Intent(this, PhoneCallActivity::class.java)
            startActivity(call)
        }
        // Accessing the internet browser
        webBrowserImageView.setOnClickListener {
            Toast.makeText(this, "Search on the web", Toast.LENGTH_SHORT).show()
            val web = Intent(this, WebBrowserActivity::class.java)
            startActivity(web)
        }
        // Sending an email
        emailImageView.setOnClickListener {
            Toast.makeText(this, "Send an email", Toast.LENGTH_SHORT).show()
            val sendEmail = Intent(this, SendEmailActivity::class.java)
            startActivity(sendEmail)
        }
        // Sending SMS
        smsImageView.setOnClickListener {
            val sms = Intent(this, SendMessageActivity::class.java)
            startActivity(sms)
            Toast.makeText(this, "Sending sms", Toast.LENGTH_SHORT).show()
        }
        // Music
        musicImageView.setOnClickListener {
            val music = Intent(this, PlayMusicActivity::class.java)
            startActivity(music)
            Toast.makeText(this, "Playing Music", Toast.LENGTH_SHORT).show()
        }

        // bottom navigation
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> navigateToActivity(MainMenuActivity::class.java, "Home")
                R.id.nav_profile -> navigateToActivity(MyProfileActivity::class.java, "Your Profile")
                R.id.nav_settings -> navigateToActivity(SettingsActivity::class.java, "Welcome To Settings")
                R.id.nav_help -> navigateToActivity(HelpActivity::class.java, "Welcome To Help")
            }
            true
        }
    }

    private fun navigateToActivity(activityClass: Class<*>, toastMessage: String) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
    }
}

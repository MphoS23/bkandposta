package com.madassignment.pama.Dashboard

// Done Code & XML

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.madassignment.pama.R

import android.content.Intent
import android.content.SharedPreferences
import android.widget.Button
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.auth.FirebaseAuth
import com.madassignment.pama.Activities.LoginActivity

class SettingsActivity : AppCompatActivity() {

    companion object {
        const val THEME_PREFS = "theme_prefs"
        const val THEME_KEY = "theme_key"
    }

    private lateinit var logoutBtn: Button
    private lateinit var themeSwitch: Switch
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        logoutBtn = findViewById(R.id.logoutBtn)
        themeSwitch = findViewById(R.id.themeSwitch)
        sharedPreferences = getSharedPreferences(THEME_PREFS, MODE_PRIVATE)

        logoutBtn.setOnClickListener {
            logoutUser()
        }

        val isDarkMode = sharedPreferences.getBoolean(THEME_KEY, false)
        themeSwitch.isChecked = isDarkMode
        setTheme(isDarkMode)

        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            setTheme(isChecked)
            saveThemePreference(isChecked)
        }
    }

    private fun setTheme(isDarkMode: Boolean) {
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun saveThemePreference(isDarkMode: Boolean) {
        sharedPreferences.edit().putBoolean(THEME_KEY, isDarkMode).apply()
    }

    private fun logoutUser() {
        // Clear any user session data or credentials here
        FirebaseAuth.getInstance().signOut()

        // Navigate to the login screen
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish() // Finish the current activity to prevent returning to it from the back stack
    }

}

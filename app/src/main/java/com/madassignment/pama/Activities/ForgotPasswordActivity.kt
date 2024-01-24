package com.madassignment.pama.Activities

// Done Code & XML

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.madassignment.pama.R
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var resetButton: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        emailEditText = findViewById(R.id.editText_email)
        resetButton = findViewById(R.id.button_reset_password)
        auth = FirebaseAuth.getInstance()

        resetButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(
                    this@ForgotPasswordActivity,
                    "Please enter your email",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                sendPasswordResetEmail(email)
            }
        }
    }

    private fun sendPasswordResetEmail(email: String) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this@ForgotPasswordActivity,
                        "Password reset email sent to $email",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish() // Close the activity after sending the reset email
                } else {
                    Toast.makeText(
                        this@ForgotPasswordActivity,
                        "Failed to send password reset email. Please check your email address",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}

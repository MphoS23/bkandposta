package com.madassignment.pama.Activities

// Done Code & XML

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import com.madassignment.pama.R
import android.text.TextUtils
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        val emailEditText: EditText = findViewById(R.id.emailUserEditText)
        val passwordEditText: EditText = findViewById(R.id.paswordEditTextPassword)
        val loginBtn: Button = findViewById(R.id.loginBtn)
        val registerTextView: TextView = findViewById(R.id.RegisterTextView)
        val forgetPass: TextView = findViewById(R.id.forgetPassTextView)
        val textView2: TextView = findViewById(R.id.textView2)

        val spannableString = SpannableStringBuilder("By signing in you are agreeing \nour ")
        val termsAndPrivacyString = "term and privacy policy"
        val start = spannableString.length
        spannableString.append(termsAndPrivacyString)

        // Make it bold
        spannableString.setSpan(
            StyleSpan(Typeface.BOLD),
            start,
            spannableString.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // Set the color
        spannableString.setSpan(
            ForegroundColorSpan(Color.BLUE),
            start,
            spannableString.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        textView2.text = spannableString

        loginBtn.setOnClickListener {
            val txtEmail = emailEditText.text.toString().trim()
            val txtPassword = passwordEditText.text.toString()

            if (TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPassword)) {
                Toast.makeText(this@LoginActivity, "Empty Credentials", Toast.LENGTH_SHORT).show()
            } else {
                loginUser(txtEmail, txtPassword)
            }
        }

        registerTextView.setOnClickListener {
            val signUpIntent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(signUpIntent)
            Toast.makeText(this@LoginActivity, "SignUp", Toast.LENGTH_SHORT).show()
        }
        forgetPass.setOnClickListener {
            val signUpIntent = Intent(this@LoginActivity, ForgotPasswordActivity::class.java)
            startActivity(signUpIntent)
            Toast.makeText(this@LoginActivity, "SignUp", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loginUser(username: String, password: String) {
        mAuth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener(this@LoginActivity, OnCompleteListener<AuthResult> { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this@LoginActivity, "Login Success", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@LoginActivity, MainMenuActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    finish()
                }
            }).addOnFailureListener(this@LoginActivity, OnFailureListener { e ->
                Toast.makeText(this@LoginActivity, e.message.toString(), Toast.LENGTH_SHORT).show()
            })
    }
}


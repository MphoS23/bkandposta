package com.madassignment.pama.Activities

// Done Code & XML

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.madassignment.pama.R
import java.util.*

class RegisterActivity : AppCompatActivity() {

    // Firebase
    private lateinit var mRootRef: DatabaseReference
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Firebase initialization
        mRootRef = FirebaseDatabase.getInstance().reference
        mAuth = FirebaseAuth.getInstance()

        val firstNameEditText: EditText = findViewById(R.id.firstNameEditText)
        val lastNameEditText: EditText = findViewById(R.id.lastNameEditText)
        val emailEditText: EditText = findViewById(R.id.emailEditText)
        val genderEditText: EditText = findViewById(R.id.genderEditText)
        val DOBEditText: EditText = findViewById(R.id.DOBEditText)
        val addressEditText: EditText = findViewById(R.id.addressEditText)
        val phoneEditText: EditText = findViewById(R.id.editTextPhone)
        val passwordEditText: EditText = findViewById(R.id.editTextTextPassword)
        val rePassEditText: EditText = findViewById(R.id.rePassEditText)

        val signUpBtn: Button = findViewById(R.id.signUpBtn)

        signUpBtn.setOnClickListener {
            val email = emailEditText.text.toString()
            val firstName = firstNameEditText.text.toString().trim()
            val lastName = lastNameEditText.text.toString().trim()
            val gender = genderEditText.text.toString().trim()
            val DOB = DOBEditText.text.toString().trim()
            val address = addressEditText.text.toString().trim()
            val phoneNumber = phoneEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val rePassword = rePassEditText.text.toString().trim()

            // Validating EditTexts if they are empty
            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || gender.isEmpty()
                || DOB.isEmpty() || address.isEmpty() || phoneNumber.isEmpty()
                || password.isEmpty() || rePassword.isEmpty()
            ) {
                // Alert Dialogue
                val builder = AlertDialog.Builder(this)
                builder.setCancelable(true)
                builder.setTitle("Fill in everything")
                builder.setMessage("Fields are empty!! Please fill in everything")
                builder.show()
            } else if (password.length < 6 || rePassword.length < 6) {
                val builder = AlertDialog.Builder(this)
                builder.setCancelable(true)
                builder.setTitle("Short Assignment")
                builder.setMessage("Make sure your passwords have more than 6 digits!!")
                builder.show()
            } else if (password != rePassword) {
                val builder = AlertDialog.Builder(this)
                builder.setCancelable(true)
                builder.setTitle("Passwords do not match")
                builder.setMessage("Passwords are not the same, please enter the same passwords!!")
                builder.show()
            } else {
                registerUser(
                    email,
                    firstName,
                    lastName,
                    gender,
                    DOB,
                    address,
                    phoneNumber,
                    password,
                    rePassword
                )
            }
        }
    }

    private fun registerUser(
        email: String, firstName: String, lastName: String, gender: String, DOB: String,
        address: String, phoneNumber: String, password: String, rePassword: String
    ) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener(
                this@RegisterActivity,
                OnSuccessListener<AuthResult> { authResult ->
                    val map: MutableMap<String, Any> = HashMap()
                    map["email"] = email
                    map["firstName"] = firstName
                    map["lastName"] = lastName
                    map["gender"] = gender
                    map["DOB"] = DOB
                    map["Address"] = address
                    map["PhoneNumber"] = phoneNumber
                    map["Password"] = password
                    map["RePassword"] = rePassword
                    mRootRef.child("Users").child(mAuth.currentUser!!.uid).setValue(map)
                        .addOnCompleteListener(
                            this@RegisterActivity,
                            OnCompleteListener<Void?> { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(
                                        this@RegisterActivity,
                                        "Successfully Registered",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    val intent =
                                        Intent(this@RegisterActivity, LoginActivity::class.java)
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                    startActivity(intent)
                                    finish()
                                }
                            })
                })
            .addOnFailureListener(this@RegisterActivity, OnFailureListener { e ->
                Toast.makeText(this@RegisterActivity, e.message.toString(), Toast.LENGTH_LONG)
                    .show()
            })
    }
}

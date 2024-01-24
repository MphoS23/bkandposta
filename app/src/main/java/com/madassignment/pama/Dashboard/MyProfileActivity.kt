package com.madassignment.pama.Dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.madassignment.pama.R
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.madassignment.pama.Model.User

class MyProfileActivity : AppCompatActivity() {

    private lateinit var firstNameTextView: TextView
    private lateinit var lastNameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var genderTextView: TextView
    private lateinit var addressTextView: TextView
    private lateinit var editBtn: ImageButton

    private lateinit var databaseReference: DatabaseReference
    private lateinit var currentUser: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)

        // Initialize Views
        firstNameTextView = findViewById(R.id.setFirstNameTextView)
        lastNameTextView = findViewById(R.id.setLastNameTextView)
        emailTextView = findViewById(R.id.setEmailTextView)
        genderTextView = findViewById(R.id.setGenderTextView)
        addressTextView = findViewById(R.id.setAddressTextView)
        editBtn = findViewById(R.id.editBtn)

        // Initialize Firebase
        currentUser = FirebaseAuth.getInstance().currentUser!!
        databaseReference = FirebaseDatabase.getInstance().reference.child("Users").child(currentUser.uid)

        editBtn.setOnClickListener {
            Toast.makeText(this, "Update Your Details", Toast.LENGTH_SHORT).show()
            val call = Intent(this, UpdateProfileActivity::class.java)
            startActivity(call)
        }

        // Retrieve user details from Firebase Realtime Database
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(User::class.java)
                user?.let {
                    // Set retrieved details to TextViews
                    firstNameTextView.text = user.firstName
                    lastNameTextView.text = user.lastName
                    emailTextView.text = user.email
                    genderTextView.text = user.gender
                    addressTextView.text = user.address
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle database error
                Toast.makeText(this@MyProfileActivity, "Failed to retrieve data", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

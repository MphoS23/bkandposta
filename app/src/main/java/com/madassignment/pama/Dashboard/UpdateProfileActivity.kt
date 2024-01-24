package com.madassignment.pama.Dashboard

// Done coding for EditProfileActivity.kt & XML

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.madassignment.pama.R
import com.madassignment.pama.Model.User

class UpdateProfileActivity : AppCompatActivity() {

    private lateinit var updateBtn: Button
    private lateinit var updateEmail: EditText
    private lateinit var updateFirstName: EditText
    private lateinit var updateLastName: EditText
    private lateinit var updateGender: EditText
    private lateinit var updateAddress: EditText

    private lateinit var currentUser: FirebaseUser
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)

        // Initialize Views
        updateBtn = findViewById(R.id.updateBtn)
        updateEmail = findViewById(R.id.updateEmail)
        updateFirstName = findViewById(R.id.updateFirstName)
        updateLastName = findViewById(R.id.updateLastName)
        updateGender = findViewById(R.id.updateGender)
        updateAddress = findViewById(R.id.updateAddress)

        // Initialize Firebase
        currentUser = FirebaseAuth.getInstance().currentUser!!
        databaseReference = FirebaseDatabase.getInstance().reference.child("Users").child(currentUser.uid)

        // Retrieve user details from Firebase Realtime Database and set in EditTexts
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(User::class.java)
                user?.let {
                    updateEmail.setText(user.email)
                    updateFirstName.setText(user.firstName)
                    updateLastName.setText(user.lastName)
                    updateGender.setText(user.gender)
                    updateAddress.setText(user.address)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle database error
            }
        })

        // Update button click listener
        updateBtn.setOnClickListener {
            val updatedEmail = updateEmail.text.toString()
            val updatedFirstName = updateFirstName.text.toString()
            val updatedLastName = updateLastName.text.toString()
            val updatedGender = updateGender.text.toString()
            val updatedAddress = updateAddress.text.toString()

            // Update user details in Firebase Realtime Database
            databaseReference.child("email").setValue(updatedEmail)
            databaseReference.child("firstName").setValue(updatedFirstName)
            databaseReference.child("lastName").setValue(updatedLastName)
            databaseReference.child("gender").setValue(updatedGender)
            databaseReference.child("address").setValue(updatedAddress)

            // Navigate back to ProfileActivity
            finish()
        }
    }
}

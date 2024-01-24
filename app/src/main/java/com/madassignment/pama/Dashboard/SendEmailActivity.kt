package com.madassignment.pama.Dashboard

// Done Code & XML

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.madassignment.pama.R

import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class SendEmailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_email)

        // Initializing and declaring

        // TextViews
        val recipientTxtView: TextView = findViewById(R.id.recipientTxtView)
        val subjectTxtView: TextView = findViewById(R.id.subjectTxtView)
        val messageTxtView: TextView = findViewById(R.id.messageTxtView)

        // EditTexts
        val recipient: EditText = findViewById(R.id.recipient)
        val subject: EditText = findViewById(R.id.subject)
        val message: EditText = findViewById(R.id.message)

        // Buttons
        val sendBtn: Button = findViewById(R.id.sendBtn)

        sendBtn.setOnClickListener {
            val receiver = recipient.text.toString().trim()
            val sub = subject.text.toString().trim()
            val msg = message.text.toString().trim()

            // Validating if it is empty or not
            if (receiver.isEmpty() || sub.isEmpty() || msg.isEmpty()) {
                Toast.makeText(this@SendEmailActivity, "Please check if everything is filled out", Toast.LENGTH_SHORT).show()
            } else {
                val email = Intent(Intent.ACTION_SEND)
                email.putExtra(Intent.EXTRA_EMAIL, arrayOf(receiver))
                email.putExtra(Intent.EXTRA_SUBJECT, sub)
                email.putExtra(Intent.EXTRA_TEXT, msg)

                // need this to prompt email client only
                email.type = "message/rfc822"

                startActivity(Intent.createChooser(email, "Choose an Email client :"))
            }
        }
    }

    private fun sendEmail(receiver: String, subject: String, message: String) {
        val email = Intent(Intent.ACTION_SEND)
        email.putExtra(Intent.EXTRA_EMAIL, arrayOf(receiver))
        email.putExtra(Intent.EXTRA_SUBJECT, subject)
        email.putExtra(Intent.EXTRA_TEXT, message)
        email.type = "message/rfc822"

        startActivity(Intent.createChooser(email, "Choose an Email client:"))
    }
}

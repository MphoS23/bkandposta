package com.madassignment.pama.Dashboard

// Done Code & XML

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.madassignment.pama.R

class SendMessageActivity : AppCompatActivity() {

    private lateinit var btnSend: Button
    private lateinit var etContact: EditText
    private lateinit var etMessage: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_message)

        btnSend = findViewById(R.id.btn_send)
        etContact = findViewById(R.id.et_contact)
        etMessage = findViewById(R.id.et_message)

        permissionToConnect()

        btnSend.setOnClickListener {
            val number = etContact.text.toString()
            val message = etMessage.text.toString()

            try {
                val smsManager: SmsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(number, null, message, null, null)
                Toast.makeText(this@SendMessageActivity, "Sent", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this@SendMessageActivity, "Sending Failed", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun permissionToConnect() {
        if (ContextCompat.checkSelfPermission(
                this@SendMessageActivity,
                Manifest.permission.SEND_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this@SendMessageActivity,
                    Manifest.permission.SEND_SMS
                )
            ) {
                ActivityCompat.requestPermissions(
                    this@SendMessageActivity,
                    arrayOf(Manifest.permission.SEND_SMS),
                    1
                )
            } else {
                ActivityCompat.requestPermissions(
                    this@SendMessageActivity,
                    arrayOf(Manifest.permission.SEND_SMS),
                    1
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(
                        this@SendMessageActivity,
                        Manifest.permission.SEND_SMS
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    Toast.makeText(this@SendMessageActivity, "Access", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this@SendMessageActivity, "Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

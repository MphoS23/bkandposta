package com.madassignment.pama.Dashboard

// Done Code & XML
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.madassignment.pama.R

class PhoneCallActivity : AppCompatActivity() {
    private val TAG: String = PhoneCallActivity::class.java.simpleName
    private val MULTIPLE_PERMISSIONS = 101

    private lateinit var number: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_call)
        number = findViewById(R.id.mobnum)

        // ask for runtime permission
        checkPermissions()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MULTIPLE_PERMISSIONS -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // Call on Button click
    fun dialCall(view: View) {
        val num = number.text.toString()
        if (num.isNotEmpty()) {
            val noToDial = "tel:$num"
            val number = Uri.parse(noToDial)
            val callIntent = Intent(Intent.ACTION_CALL, number)
            if (checkPermissions()) {
                startActivity(callIntent)
            }
        } else {
            Toast.makeText(this, "Please enter number", Toast.LENGTH_SHORT).show()
        }
    }

    // For runtime Permission
    private fun checkPermissions(): Boolean {
        val permissions = arrayOf(Manifest.permission.CALL_PHONE)

        val listPermissionsNeeded = mutableListOf<String>()
        for (p in permissions) {
            val result = ContextCompat.checkSelfPermission(this, p)
            if (result != PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "Ask Permissions : $p")
                listPermissionsNeeded.add(p)
            }
        }
        if (listPermissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                listPermissionsNeeded.toTypedArray(),
                MULTIPLE_PERMISSIONS
            )
            return false
        }
        return true
    }
}

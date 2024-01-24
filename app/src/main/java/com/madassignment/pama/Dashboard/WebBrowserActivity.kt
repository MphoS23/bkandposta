package com.madassignment.pama.Dashboard


// DOne Code & XML
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.madassignment.pama.R

import android.app.SearchManager
import android.content.Intent
import android.view.View
import android.widget.EditText

class WebBrowserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_browser)
        // Attach an instance of HandleClick to the Button
        findViewById<View>(R.id.imageButton).setOnClickListener(HandleClick())
    }

    private inner class HandleClick : View.OnClickListener {
        override fun onClick(arg0: View) {
            val searchFor = (findViewById<EditText>(R.id.editText)).text.toString()
            val viewSearch = Intent(Intent.ACTION_WEB_SEARCH)
            viewSearch.putExtra(SearchManager.QUERY, searchFor)
            startActivity(viewSearch)
        }
    }
}

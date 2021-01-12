package com.faircorp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.faircorp.model.WindowAdapter
import com.faircorp.model.WindowService

const val WINDOW_NAME_PARAM = "com.faircorp.windowname.attribute"

class MainActivity : BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun openWindow(view: View) {
        val windowName = findViewById<EditText>(R.id.txt_window_name).text.toString()

        // Do something in response to button
        val intent = Intent(this, WindowActivity::class.java).apply {
            putExtra(WINDOW_NAME_PARAM, windowName)
        }
        startActivity(intent)
    }
}
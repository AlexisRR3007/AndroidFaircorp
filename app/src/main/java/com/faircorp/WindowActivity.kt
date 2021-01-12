package com.faircorp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class WindowActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val param = intent.getStringExtra(WINDOW_NAME_PARAM)
        val windowName = findViewById<TextView>(R.id.window_txt_window_name)
        windowName.text = param
    }
}
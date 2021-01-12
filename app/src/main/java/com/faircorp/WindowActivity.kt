package com.faircorp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.faircorp.model.ApiServices
import com.faircorp.model.WindowDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WindowActivity : BasicActivity() {

    var id = 0L;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        id = intent.getLongExtra(WINDOW_ID_PARAM,0)
        val name = intent.getStringExtra(WINDOW_NAME_PARAM)
        val room = intent.getStringExtra(WINDOW_ROOM_PARAM)
        val curtemp = intent.getStringExtra(WINDOW_CURTEMP_PARAM)
        val tartemp = intent.getStringExtra(WINDOW_TARTEMP_PARAM)
        val status = intent.getStringExtra(WINDOW_STATUS_PARAM)

        val windowName = findViewById<TextView>(R.id.act_window_txt_window_name)
        val windowRoom = findViewById<TextView>(R.id.act_window_txt_window_room)
        val windowCurTemp = findViewById<TextView>(R.id.act_window_txt_window_current_temperature)
        val windowTarTemp = findViewById<TextView>(R.id.act_window_txt_window_target_temperature)
        val windowStatus = findViewById<TextView>(R.id.act_window_txt_window_status)
        val windowStatusSwitch = findViewById<Switch>(R.id.act_window_switch_status)

        windowName.text = name
        windowRoom.text = room
        windowCurTemp.text = curtemp
        windowTarTemp.text = tartemp
        windowStatus.text = status
        windowStatusSwitch.isChecked = false;
        if(status=="OPEN") {
            windowStatusSwitch.isChecked = true;
        }

    }

    fun onStatusSwitch(view: View) {

        val windowStatusSwitch = findViewById<Switch>(R.id.act_window_switch_status)
        val windowStatus = findViewById<TextView>(R.id.act_window_txt_window_status)

        // Function is after the click so isChecked=true, it means the window was closed before
        if(windowStatusSwitch.isChecked) {
            windowStatusSwitch.isClickable = false;
            lifecycleScope.launch(context = Dispatchers.IO) {
                runCatching { ApiServices().windowsApiService.switchStatusById(id).execute() }
                    .onFailure {
                        withContext(context = Dispatchers.Main) {
                            Toast.makeText(
                                applicationContext,
                                "Error on switching the status $it",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }
            windowStatus.text = "OPEN"
            windowStatusSwitch.isClickable = true;
        } else {
            windowStatusSwitch.isClickable = false;
            lifecycleScope.launch(context = Dispatchers.IO) {
                runCatching { ApiServices().windowsApiService.switchStatusById(id).execute() }
                    .onFailure {
                        withContext(context = Dispatchers.Main) {
                            Toast.makeText(
                                applicationContext,
                                "Error on switching the status $it",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }
            windowStatus.text = "CLOSED"
            windowStatusSwitch.isClickable = true;
        }
    }
}
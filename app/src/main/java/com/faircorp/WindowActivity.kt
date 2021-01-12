package com.faircorp

import android.os.Bundle
import android.view.View
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.faircorp.api.ApiServices
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
        val floor = intent.getStringExtra(WINDOW_FLOOR_PARAM)
        val building = intent.getStringExtra(WINDOW_BUILDING_PARAM)
        val curtemp = intent.getStringExtra(WINDOW_CURTEMP_PARAM)
        val tartemp = intent.getStringExtra(WINDOW_TARTEMP_PARAM)
        val status = intent.getStringExtra(WINDOW_STATUS_PARAM)

        val windowName = findViewById<TextView>(R.id.act_window_txt_window_name)
        val windowRoom = findViewById<TextView>(R.id.act_window_txt_window_room)
        val windowFloor = findViewById<TextView>(R.id.act_window_txt_window_floor)
        val windowBuilding = findViewById<TextView>(R.id.act_window_txt_window_building)
        val windowCurTemp = findViewById<TextView>(R.id.act_window_txt_window_current_temperature)
        val windowTarTemp = findViewById<TextView>(R.id.act_window_txt_window_target_temperature)
        val windowStatus = findViewById<TextView>(R.id.act_window_txt_window_status)
        val windowStatusSwitch = findViewById<Switch>(R.id.act_window_switch_status)
        val windowDeleteSwitch = findViewById<Switch>(R.id.act_window_switch_delete)
        val windowDeleteButton = findViewById<TextView>(R.id.act_window_btn_delete)

        windowName.text = name
        windowRoom.text = room
        windowFloor.text = floor
        windowBuilding.text = building
        windowCurTemp.text = curtemp
        windowTarTemp.text = tartemp
        windowStatus.text = status
        windowStatusSwitch.isChecked = false
        if(status=="OPEN") {
            windowStatusSwitch.isChecked = true
        }
        windowDeleteSwitch.isChecked = false
        windowDeleteButton.isClickable = false

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
                    .onSuccess {
                        withContext(context = Dispatchers.Main) {
                            windowStatus.text = "OPEN"
                        }
                    }
            }
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
                    .onSuccess {
                        withContext(context = Dispatchers.Main) {
                            windowStatus.text = "CLOSED"
                        }
                    }
            }
            windowStatusSwitch.isClickable = true;
        }
    }

    fun onDeleteSwitch(view: View) {
        val windowDeleteSwitch = findViewById<Switch>(R.id.act_window_switch_delete)
        val windowDeleteButton = findViewById<TextView>(R.id.act_window_btn_delete)

        if(windowDeleteSwitch.isChecked) {
            windowDeleteButton.isClickable = true
        } else {
            windowDeleteButton.isClickable = false
        }
    }

    fun onDeleteWindow(view: View) {

        val windowStatusSwitch = findViewById<Switch>(R.id.act_window_switch_status)
        val windowStatus = findViewById<TextView>(R.id.act_window_txt_window_status)
        val act = this

            lifecycleScope.launch(context = Dispatchers.IO) {
                runCatching { ApiServices().windowsApiService.deleteById(id).execute() }
                    .onFailure {
                        withContext(context = Dispatchers.Main) {
                            Toast.makeText(
                                applicationContext,
                                "Error on switching the status $it",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                    .onSuccess {
                        withContext(context = Dispatchers.Main) {
                            act.finish()
                        }
                    }

        }
    }
}
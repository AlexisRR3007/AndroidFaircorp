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

class HeaterActivity : BasicActivity() {

    var id = 0L;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heater)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        id = intent.getLongExtra(HEATER_ID_PARAM,0)
        val name = intent.getStringExtra(HEATER_NAME_PARAM)
        val room = intent.getStringExtra(HEATER_ROOM_PARAM)
        val floor = intent.getStringExtra(HEATER_FLOOR_PARAM)
        val building = intent.getStringExtra(HEATER_BUILDING_PARAM)
        val curtemp = intent.getStringExtra(HEATER_CURTEMP_PARAM)
        val tartemp = intent.getStringExtra(HEATER_TARTEMP_PARAM)
        val status = intent.getStringExtra(HEATER_STATUS_PARAM)

        val heaterName = findViewById<TextView>(R.id.act_heater_txt_heater_name)
        val heaterRoom = findViewById<TextView>(R.id.act_heater_txt_heater_room)
        val heaterFloor = findViewById<TextView>(R.id.act_heater_txt_heater_floor)
        val heaterBuilding = findViewById<TextView>(R.id.act_heater_txt_heater_building)
        val heaterCurTemp = findViewById<TextView>(R.id.act_heater_txt_heater_current_temperature)
        val heaterTarTemp = findViewById<TextView>(R.id.act_heater_txt_heater_target_temperature)
        val heaterStatus = findViewById<TextView>(R.id.act_heater_txt_heater_status)
        val heaterStatusSwitch = findViewById<Switch>(R.id.act_heater_switch_status)
        val heaterDeleteSwitch = findViewById<Switch>(R.id.act_heater_switch_delete)
        val heaterDeleteButton = findViewById<TextView>(R.id.act_heater_btn_delete)

        heaterName.text = name
        heaterRoom.text = room
        heaterFloor.text = floor
        heaterBuilding.text = building
        heaterCurTemp.text = curtemp
        heaterTarTemp.text = tartemp
        heaterStatus.text = status
        heaterStatusSwitch.isChecked = false
        if(status=="ON") {
            heaterStatusSwitch.isChecked = true
        }
        heaterDeleteSwitch.isChecked = false
        heaterDeleteButton.isClickable = false

    }

    fun onStatusSwitch(view: View) {

        val heaterStatusSwitch = findViewById<Switch>(R.id.act_heater_switch_status)
        val heaterStatus = findViewById<TextView>(R.id.act_heater_txt_heater_status)

        // Function is after the click so isChecked=true, it means the heater was closed before
        if(heaterStatusSwitch.isChecked) {
            heaterStatusSwitch.isClickable = false;
            lifecycleScope.launch(context = Dispatchers.IO) {
                runCatching { ApiServices().heatersApiService.switchStatusById(id).execute() }
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
                            heaterStatus.text = "ON"
                        }
                    }
            }
            heaterStatusSwitch.isClickable = true;
        } else {
            heaterStatusSwitch.isClickable = false;
            lifecycleScope.launch(context = Dispatchers.IO) {
                runCatching { ApiServices().heatersApiService.switchStatusById(id).execute() }
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
                            heaterStatus.text = "OFF"
                        }
                    }
            }
            heaterStatusSwitch.isClickable = true;
        }
    }

    fun onDeleteSwitch(view: View) {
        val heaterDeleteSwitch = findViewById<Switch>(R.id.act_heater_switch_delete)
        val heaterDeleteButton = findViewById<TextView>(R.id.act_heater_btn_delete)

        if(heaterDeleteSwitch.isChecked) {
            heaterDeleteButton.isClickable = true
        } else {
            heaterDeleteButton.isClickable = false
        }
    }

    fun onDeleteHeater(view: View) {

        val heaterStatusSwitch = findViewById<Switch>(R.id.act_heater_switch_status)
        val heaterStatus = findViewById<TextView>(R.id.act_heater_txt_heater_status)
        val act = this

            lifecycleScope.launch(context = Dispatchers.IO) {
                runCatching { ApiServices().heatersApiService.deleteById(id).execute() }
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
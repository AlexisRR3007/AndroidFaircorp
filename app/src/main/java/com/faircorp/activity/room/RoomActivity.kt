package com.faircorp.activity.room

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.faircorp.R
import com.faircorp.activity.*
import com.faircorp.activity.window.WindowActivity
import com.faircorp.activity.window.WindowsActivity
import com.faircorp.api.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RoomActivity : BasicActivity() {

    var id = 0L;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        id = intent.getLongExtra(ROOM_ID_PARAM,0)
        val name = intent.getStringExtra(ROOM_NAME_PARAM)
        val floor = intent.getStringExtra(ROOM_FLOOR_PARAM)
        val building = intent.getStringExtra(ROOM_BUILDING_PARAM)
        val curtemp = intent.getStringExtra(ROOM_CURTEMP_PARAM)
        val tartemp = intent.getStringExtra(ROOM_TARTEMP_PARAM)

        val roomName = findViewById<TextView>(R.id.act_room_txt_room_name)
        val roomFloor = findViewById<TextView>(R.id.act_room_txt_room_floor)
        val roomBuilding = findViewById<TextView>(R.id.act_room_txt_room_building)
        val roomCurTemp = findViewById<TextView>(R.id.act_room_txt_room_current_temperature)
        val roomTarTemp = findViewById<TextView>(R.id.act_room_txt_room_target_temperature)
        val roomDeleteSwitch = findViewById<Switch>(R.id.act_room_switch_delete)
        val roomDeleteButton = findViewById<TextView>(R.id.act_room_btn_delete)

        roomName.text = name
        roomFloor.text = floor
        roomBuilding.text = building
        roomCurTemp.text = curtemp
        roomTarTemp.text = tartemp
        roomDeleteSwitch.isChecked = false
        roomDeleteButton.isClickable = false

    }

    fun onViewWindows(view: View) {
        val intent = Intent(this, RoomWindowsActivity::class.java)
                .putExtra(ROOM_ID_PARAM, id)

        startActivity(intent)
    }

    fun onDeleteSwitch(view: View) {
        val roomDeleteSwitch = findViewById<Switch>(R.id.act_room_switch_delete)
        val roomDeleteButton = findViewById<TextView>(R.id.act_room_btn_delete)

        if(roomDeleteSwitch.isChecked) {
            roomDeleteButton.isClickable = true
        } else {
            roomDeleteButton.isClickable = false
        }
    }

    fun onDeleteRoom(view: View) {

        val act = this

            lifecycleScope.launch(context = Dispatchers.IO) {
                runCatching { ApiServices().roomsApiService.deleteById(id).execute() }
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
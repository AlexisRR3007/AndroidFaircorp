package com.faircorp.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.faircorp.R
import com.faircorp.activity.heater.HeatersActivity
import com.faircorp.activity.room.RoomsActivity
import com.faircorp.activity.window.WindowsActivity

const val WINDOW_ID_PARAM = "com.faircorp.windowid.attribute"
const val WINDOW_NAME_PARAM = "com.faircorp.windowname.attribute"
const val WINDOW_ROOM_PARAM = "com.faircorp.windowroom.attribute"
const val WINDOW_FLOOR_PARAM = "com.faircorp.windowfloor.attribute"
const val WINDOW_BUILDING_PARAM = "com.faircorp.windowbuilding.attribute"
const val WINDOW_STATUS_PARAM = "com.faircorp.windowstatus.attribute"
const val WINDOW_CURTEMP_PARAM = "com.faircorp.windowcurtemp.attribute"
const val WINDOW_TARTEMP_PARAM = "com.faircorp.windowtartemp.attribute"

const val HEATER_ID_PARAM = "com.faircorp.heaterid.attribute"
const val HEATER_NAME_PARAM = "com.faircorp.heatername.attribute"
const val HEATER_ROOM_PARAM = "com.faircorp.heaterroom.attribute"
const val HEATER_FLOOR_PARAM = "com.faircorp.heaterfloor.attribute"
const val HEATER_BUILDING_PARAM = "com.faircorp.heaterbuilding.attribute"
const val HEATER_STATUS_PARAM = "com.faircorp.heaterstatus.attribute"
const val HEATER_CURTEMP_PARAM = "com.faircorp.heatercurtemp.attribute"
const val HEATER_TARTEMP_PARAM = "com.faircorp.heatertartemp.attribute"

const val ROOM_ID_PARAM = "com.faircorp.roomid.attribute"
const val ROOM_NAME_PARAM = "com.faircorp.roomname.attribute"
const val ROOM_FLOOR_PARAM = "com.faircorp.roomfloor.attribute"
const val ROOM_BUILDING_PARAM = "com.faircorp.roombuilding.attribute"
const val ROOM_CURTEMP_PARAM = "com.faircorp.roomcurtemp.attribute"
const val ROOM_TARTEMP_PARAM = "com.faircorp.roomtartemp.attribute"

/**
 * Main activity of the app
 */
class MainActivity : BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun manageWindows(view: View) {

        val intent = Intent(this, WindowsActivity::class.java)

        startActivity(intent)
    }

    fun manageHeaters(view: View) {

        val intent = Intent(this, HeatersActivity::class.java)

        startActivity(intent)
    }

    fun manageRooms(view: View) {

        val intent = Intent(this, RoomsActivity::class.java)

        startActivity(intent)
    }
}
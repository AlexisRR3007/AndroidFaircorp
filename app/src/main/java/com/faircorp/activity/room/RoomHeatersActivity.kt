package com.faircorp.activity.room

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faircorp.R
import com.faircorp.activity.*
import com.faircorp.api.ApiServices
import com.faircorp.api.heater.HeaterDto
import com.faircorp.model.heater.HeatersAdapterView
import com.faircorp.model.heater.OnHeaterSelectedListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Activity which gives a list of heaters of a room, access only by RoomActivity
 */
class RoomHeatersActivity : BasicActivity(), OnHeaterSelectedListener {

    var adapter: HeatersAdapterView? = null
    var idRoom: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heaters)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        idRoom = intent.getLongExtra(ROOM_ID_PARAM, 0)

        val recyclerView = findViewById<RecyclerView>(R.id.act_heaters_list)
        adapter = HeatersAdapterView(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices().roomsApiService.findAllHeatersFromRoom(idRoom!!).execute() }
                    .onSuccess {
                        withContext(context = Dispatchers.Main) {
                            adapter?.update(it.body() ?: emptyList())
                        }
                    }
                    .onFailure {
                        withContext(context = Dispatchers.Main) {
                            Toast.makeText(
                                    applicationContext,
                                    "Error on heaters loading $it",
                                    Toast.LENGTH_LONG
                            ).show()
                        }
                    }
        }

    }

    override fun onResume() {

        super.onResume()
        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices().roomsApiService.findAllHeatersFromRoom(idRoom!!).execute() }
                    .onSuccess {
                        withContext(context = Dispatchers.Main) {
                            adapter?.update(it.body() ?: emptyList())
                        }
                    }
                    .onFailure {
                        withContext(context = Dispatchers.Main) {
                            Toast.makeText(
                                    applicationContext,
                                    "Error on heaters loading $it",
                                    Toast.LENGTH_LONG
                            ).show()
                        }
                    }

        }

    }

    override fun onHeaterSelected(heater: HeaterDto) {

        val intent = Intent(this, RoomHeaterActivity::class.java)
                .putExtra(HEATER_ID_PARAM, heater.id)
                .putExtra(HEATER_NAME_PARAM, heater.name)
                .putExtra(HEATER_ROOM_PARAM, heater.room.name)
                .putExtra(HEATER_FLOOR_PARAM, heater.room.floor.floorNumber.toString())
                .putExtra(HEATER_BUILDING_PARAM, heater.room.floor.building.name)
                .putExtra(HEATER_CURTEMP_PARAM, heater.room.currentTemperature?.toString())
                .putExtra(HEATER_TARTEMP_PARAM, heater.room.targetTemperature?.toString())
                .putExtra(HEATER_STATUS_PARAM, heater.heaterStatus.toString())

        startActivity(intent)

    }

}
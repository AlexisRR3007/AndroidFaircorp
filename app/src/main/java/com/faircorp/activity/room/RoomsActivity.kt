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
import com.faircorp.api.room.RoomDto
import com.faircorp.model.room.OnRoomSelectedListener
import com.faircorp.model.room.RoomsAdapterView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Activity which gives a list of rooms, access only by MainActivity
 */
class RoomsActivity : BasicActivity(), OnRoomSelectedListener {

    var adapter: RoomsAdapterView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rooms)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val recyclerView = findViewById<RecyclerView>(R.id.act_rooms_list)
        adapter = RoomsAdapterView(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices().roomsApiService.findAll().execute() }
                    .onSuccess {
                        withContext(context = Dispatchers.Main) {
                            adapter?.update(it.body() ?: emptyList())
                        }
                    }
                    .onFailure {
                        withContext(context = Dispatchers.Main) {
                            Toast.makeText(
                                    applicationContext,
                                    "Error on rooms loading $it",
                                    Toast.LENGTH_LONG
                            ).show()
                        }
                    }
        }

    }

    // Update the data if a modification was done in a room item
    override fun onResume() {

        super.onResume()
        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices().roomsApiService.findAll().execute() }
                    .onSuccess {
                        withContext(context = Dispatchers.Main) {
                            adapter?.update(it.body() ?: emptyList())
                        }
                    }
                    .onFailure {
                        withContext(context = Dispatchers.Main) {
                            Toast.makeText(
                                    applicationContext,
                                    "Error on rooms loading $it",
                                    Toast.LENGTH_LONG
                            ).show()
                        }
                    }
        }

    }

    override fun onRoomSelected(room: RoomDto) {

        val intent = Intent(this, RoomActivity::class.java)
                .putExtra(ROOM_ID_PARAM, room.id)
                .putExtra(ROOM_NAME_PARAM, room.name)
                .putExtra(ROOM_FLOOR_PARAM, room.floor.floorNumber.toString())
                .putExtra(ROOM_BUILDING_PARAM, room.floor.building.name)
                .putExtra(ROOM_CURTEMP_PARAM, room.currentTemperature?.toString())
                .putExtra(ROOM_TARTEMP_PARAM, room.targetTemperature?.toString())

        startActivity(intent)

    }
}
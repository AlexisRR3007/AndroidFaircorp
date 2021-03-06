package com.faircorp.activity.window

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
import com.faircorp.api.window.WindowDto
import com.faircorp.model.window.OnWindowSelectedListener
import com.faircorp.model.window.WindowsAdapterView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Activity which gives the list of all windows, access only by MainActivity
 */
class WindowsActivity : BasicActivity(), OnWindowSelectedListener {

    var adapter: WindowsAdapterView? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_windows)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val recyclerView = findViewById<RecyclerView>(R.id.act_windows_list)
        adapter = WindowsAdapterView(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices().windowsApiService.findAll().execute() }
                    .onSuccess {
                        withContext(context = Dispatchers.Main) {
                            adapter?.update(it.body() ?: emptyList())
                        }
                    }
                    .onFailure {
                        withContext(context = Dispatchers.Main) {
                            Toast.makeText(
                                    applicationContext,
                                    "Error on windows loading $it",
                                    Toast.LENGTH_LONG
                            ).show()
                        }
                    }
        }

    }

    // Update the data if a modification was done in a heater item
    override fun onResume() {

        super.onResume()
        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices().windowsApiService.findAll().execute() }
                    .onSuccess {
                        withContext(context = Dispatchers.Main) {
                            adapter?.update(it.body() ?: emptyList())
                        }
                    }
                    .onFailure {
                        withContext(context = Dispatchers.Main) {
                            Toast.makeText(
                                    applicationContext,
                                    "Error on windows loading $it",
                                    Toast.LENGTH_LONG
                            ).show()
                        }
                    }
        }

    }

    override fun onWindowSelected(window: WindowDto) {

        val intent = Intent(this, WindowActivity::class.java)
                .putExtra(WINDOW_ID_PARAM, window.id)
                .putExtra(WINDOW_NAME_PARAM, window.name)
                .putExtra(WINDOW_ROOM_PARAM, window.room.name)
                .putExtra(WINDOW_FLOOR_PARAM, window.room.floor.floorNumber.toString())
                .putExtra(WINDOW_BUILDING_PARAM, window.room.floor.building.name)
                .putExtra(WINDOW_CURTEMP_PARAM, window.room.currentTemperature?.toString())
                .putExtra(WINDOW_TARTEMP_PARAM, window.room.targetTemperature?.toString())
                .putExtra(WINDOW_STATUS_PARAM, window.windowStatus.toString())

        startActivity(intent)

    }

}
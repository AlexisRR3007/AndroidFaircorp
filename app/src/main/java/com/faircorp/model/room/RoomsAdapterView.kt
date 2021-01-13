package com.faircorp.model.room

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.faircorp.R
import com.faircorp.api.room.RoomDto

class RoomsAdapterView(val listener: OnRoomSelectedListener): RecyclerView.Adapter<RoomsAdapterView.RoomViewHolder>() { // (1)

    inner class RoomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.act_rooms_item_txt_room_floor)
        val floor: TextView = view.findViewById(R.id.act_rooms_item_txt_room_floor)
        val building: TextView = view.findViewById(R.id.act_rooms_item_txt_room_building)
    }

    private val items = mutableListOf<RoomDto>()

    fun update(rooms: List<RoomDto>) {
        items.clear()
        items.addAll(rooms)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_rooms_item, parent, false)
        return RoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = items[position]
        holder.apply {
            name.text = room.name
            floor.text = room.floor.floorNumber.toString()
            building.text =  room.floor.building.name
            itemView.setOnClickListener { listener.onRoomSelected(room) } // (1)
        }
    }

    override fun onViewRecycled(holder: RoomViewHolder) { // (2)
        super.onViewRecycled(holder)
        holder.apply {
            itemView.setOnClickListener(null)
        }

    }
}
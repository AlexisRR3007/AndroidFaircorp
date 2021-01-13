package com.faircorp.model.room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.faircorp.R
import com.faircorp.api.room.RoomDto

/**
 * Adapter for the room RecycleView
 */
class RoomsAdapterView(val listener: OnRoomSelectedListener) : RecyclerView.Adapter<RoomsAdapterView.RoomViewHolder>() { // (1)

    inner class RoomViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val name: TextView = view.findViewById(R.id.act_rooms_item_txt_room_name)
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
            floor.text = "Floor " + room.floor.floorNumber.toString()
            building.text = room.floor.building.name
            itemView.setOnClickListener { listener.onRoomSelected(room) }

        }
    }

    override fun onViewRecycled(holder: RoomViewHolder) {

        super.onViewRecycled(holder)
        holder.apply {
            itemView.setOnClickListener(null)
        }

    }

}
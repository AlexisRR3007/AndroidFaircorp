package com.faircorp.model.room

import com.faircorp.api.room.RoomDto

/**
 * Interface which define a method to call when user click
 * on a room item in the RecycleView
 */
interface OnRoomSelectedListener {
    fun onRoomSelected(room: RoomDto)
}
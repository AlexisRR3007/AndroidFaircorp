package com.faircorp.model.room

import com.faircorp.api.room.RoomDto

interface OnRoomSelectedListener {
    fun onRoomSelected(room : RoomDto)
}
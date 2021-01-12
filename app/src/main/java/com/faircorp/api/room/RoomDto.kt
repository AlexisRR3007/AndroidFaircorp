package com.faircorp.api.room

import com.faircorp.api.floor.FloorDto

data class RoomDto(val id: Long,
                   val name: String,
                   val floor: FloorDto,
                   val currentTemperature: Double?,
                   val targetTemperature: Double?)

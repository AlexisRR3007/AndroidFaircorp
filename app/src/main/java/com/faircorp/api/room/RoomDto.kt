package com.faircorp.api.room

import com.faircorp.api.floor.FloorDto

/**
 * Dto for a room
 */
data class RoomDto(val id: Long,
                   val name: String,
                   val floor: FloorDto,
                   val currentTemperature: Double?,
                   val targetTemperature: Double?)

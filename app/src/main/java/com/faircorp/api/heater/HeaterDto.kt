package com.faircorp.api.heater

import com.faircorp.api.room.RoomDto

enum class heaterStatus { ON, OFF }

/**
 * Dto for a heater
 */
data class HeaterDto(val id: Long,
                     val name: String,
                     val room: RoomDto,
                     val heaterStatus: heaterStatus)
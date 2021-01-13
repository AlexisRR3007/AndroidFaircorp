package com.faircorp.api.window

import com.faircorp.api.room.RoomDto

enum class windowStatus { OPEN, CLOSED }

/**
 * Dto for a window
 */
data class WindowDto(val id: Long,
                     val name: String,
                     val room: RoomDto,
                     val windowStatus: windowStatus)
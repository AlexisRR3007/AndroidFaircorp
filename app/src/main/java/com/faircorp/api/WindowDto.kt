package com.faircorp.model

import com.faircorp.api.RoomDto

enum class windowStatus { OPEN, CLOSED}

data class WindowDto(val id: Long, val name: String, val room: RoomDto, val windowStatus: windowStatus)
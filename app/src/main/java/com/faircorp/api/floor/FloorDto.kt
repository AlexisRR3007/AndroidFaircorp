package com.faircorp.api.floor

import com.faircorp.api.building.BuildingDto

data class FloorDto(val id: Long,
                        val floorNumber: Int,
                        val targetTemperature: Double?,
                        val building: BuildingDto
)
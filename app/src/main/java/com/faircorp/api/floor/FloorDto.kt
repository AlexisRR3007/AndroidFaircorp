package com.faircorp.api.floor

import com.faircorp.api.building.BuildingDto

/**
 * Dto for a floor
 */
data class FloorDto(val id: Long,
                    val floorNumber: Int,
                    val targetTemperature: Double?,
                    val building: BuildingDto
)
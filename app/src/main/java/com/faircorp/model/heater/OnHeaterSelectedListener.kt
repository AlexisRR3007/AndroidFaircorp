package com.faircorp.model.heater

import com.faircorp.api.heater.HeaterDto

/**
 * Interface which define a method to call when user click
 * on a heater item in the RecycleView
 */
interface OnHeaterSelectedListener {
    fun onHeaterSelected(heater: HeaterDto)
}
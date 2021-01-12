package com.faircorp.model.heater

import com.faircorp.api.heater.HeaterDto

interface OnHeaterSelectedListener {
    fun onHeaterSelected(heater : HeaterDto)
}
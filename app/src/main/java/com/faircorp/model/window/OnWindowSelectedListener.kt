package com.faircorp.model.window

import com.faircorp.api.window.WindowDto

interface OnWindowSelectedListener {
    fun onWindowSelected(window : WindowDto)
}
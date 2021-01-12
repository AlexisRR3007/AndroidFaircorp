package com.faircorp.model

import com.faircorp.api.window.WindowDto

interface OnWindowSelectedListener {
    fun onWindowSelected(window : WindowDto)
}
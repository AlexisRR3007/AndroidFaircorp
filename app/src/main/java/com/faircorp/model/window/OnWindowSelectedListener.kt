package com.faircorp.model.window

import com.faircorp.api.window.WindowDto

/**
 * Interface which define a method to call when user click
 * on a window item in the RecycleView
 */
interface OnWindowSelectedListener {
    fun onWindowSelected(window: WindowDto)
}
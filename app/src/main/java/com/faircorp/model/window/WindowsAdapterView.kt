package com.faircorp.model.window

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.faircorp.R
import com.faircorp.api.window.WindowDto

/**
 * Adapter for the window RecycleView
 */
class WindowsAdapterView(val listener: OnWindowSelectedListener) : RecyclerView.Adapter<WindowsAdapterView.WindowViewHolder>() { // (1)

    inner class WindowViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val name: TextView = view.findViewById(R.id.act_windows_item_txt_window_name)
        val room: TextView = view.findViewById(R.id.act_windows_item_txt_window_room)
        val status: TextView = view.findViewById(R.id.act_windows_item_txt_status)

    }

    private val items = mutableListOf<WindowDto>()

    fun update(windows: List<WindowDto>) {

        items.clear()
        items.addAll(windows)
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WindowViewHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_windows_item, parent, false)
        return WindowViewHolder(view)

    }

    override fun onBindViewHolder(holder: WindowViewHolder, position: Int) {

        val window = items[position]
        holder.apply {
            name.text = window.name
            status.text = window.windowStatus.toString()
            room.text = window.room.name
            itemView.setOnClickListener { listener.onWindowSelected(window) }
        }

    }

    override fun onViewRecycled(holder: WindowViewHolder) {

        super.onViewRecycled(holder)
        holder.apply {
            itemView.setOnClickListener(null)
        }

    }

}
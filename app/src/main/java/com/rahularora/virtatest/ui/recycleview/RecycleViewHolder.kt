package com.rahularora.virtatest.ui.recycleview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rahularora.virtatest.R

class StationsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var name: TextView = view.findViewById(R.id.tvLocationName)
    var description: TextView = view.findViewById(R.id.tvAddress)
}

class EvseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var name: TextView = view.findViewById(R.id.tvKwNumber)
}

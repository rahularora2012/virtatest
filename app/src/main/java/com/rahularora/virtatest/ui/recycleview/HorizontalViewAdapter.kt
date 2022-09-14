package com.rahularora.virtatest.ui.recycleview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahularora.virtatest.R
import com.rahularora.virtatest.api.models.Evse

class HorizontalViewAdapter(private var itemsList: List<Evse>) : RecyclerView.Adapter<EvseViewHolder>() {

    fun loadData(evses: List<Evse>) {
        itemsList = evses
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EvseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_evse, parent, false)
        return EvseViewHolder(view)
    }

    override fun onBindViewHolder(holder: EvseViewHolder, position: Int) {
        val evse = itemsList[position]
        holder.name.text = evse.connectors[0].maxKw.toString()
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}
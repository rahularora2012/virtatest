package com.rahularora.virtatest.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rahularora.virtatest.R
import com.rahularora.virtatest.api.models.Evse
import com.rahularora.virtatest.api.models.Stations


class StationsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var name: TextView = view.findViewById(R.id.tvLocationName)
    var description: TextView = view.findViewById(R.id.tvAddress)
}

class EvseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var name: TextView = view.findViewById(R.id.tvKwNumber)
}


class MainRecyclerViewAdapter(private var stations: List<Stations>) :
    RecyclerView.Adapter<StationsViewHolder>() {

    fun loadData(newstations: List<Stations>) {
        stations = newstations
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_stations, parent, false)
        return StationsViewHolder(view)
    }

    override fun onBindViewHolder(holder: StationsViewHolder, position: Int) {
        val station = stations[position]

        holder.name.text = station.name
        holder.description.text = station.city

        val recyclerViewAdapter = HorizontalViewAdapter(ArrayList())
        val horizontalRecyclerView: RecyclerView = holder.itemView.findViewById(R.id.recycler_view)
        horizontalRecyclerView.layoutManager =
            LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)

        horizontalRecyclerView.adapter = recyclerViewAdapter
        recyclerViewAdapter.loadData(station.evses)

    }

    override fun getItemCount(): Int {
        return stations.size
    }
}

class HorizontalViewAdapter(private var itemsList: List<Evse>) : RecyclerView.Adapter<EvseViewHolder>() {

    //private var itemsList: List<Evse> = ArrayList()

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
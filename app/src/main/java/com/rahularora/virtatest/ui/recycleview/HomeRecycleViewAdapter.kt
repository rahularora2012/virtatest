package com.rahularora.virtatest.ui.recycleview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rahularora.virtatest.R
import com.rahularora.virtatest.api.models.Stations

class HomeRecycleViewAdapter(private var stations: List<Stations>) :
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
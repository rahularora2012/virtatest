package com.rahularora.virtatest.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rahularora.virtatest.api.models.Stations
import com.rahularora.virtatest.utils.GsonUtils

class HomeViewModel(private val utils: GsonUtils): ViewModel() {
    private val mutablestations = MutableLiveData<List<Stations>>()
    val stations1: LiveData<List<Stations>> get() = mutablestations

    fun fetchStations(context: Context) {
        val jsonString = utils.getJson(context, "stations.json")

        val gson = Gson()
        val liststationType = object: TypeToken<List<Stations>>() {}.type

        val stations = gson.fromJson<List<Stations>>(jsonString, liststationType)
        mutablestations.value = stations
    }
}
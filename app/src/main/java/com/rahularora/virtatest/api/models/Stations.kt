package com.rahularora.virtatest.api.models

import com.rahularora.virtatest.api.models.Evse
import kotlinx.serialization.Serializable

@Serializable
data class Stations(
    val city: String,
    val evses: List<Evse>,
    val id: Int,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val provider: String)


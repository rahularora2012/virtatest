package com.rahularora.virtatest.api.models

import kotlinx.serialization.Serializable

@Serializable
data class Evse(
    val connectors: List<Connector>,
    val groupName: String,
    val id: Int
)
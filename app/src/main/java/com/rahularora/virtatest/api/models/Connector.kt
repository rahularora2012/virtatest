package com.rahularora.virtatest.api.models

import kotlinx.serialization.Serializable

@Serializable
data class Connector(
    val maxKw: Int,
    val type: String
)
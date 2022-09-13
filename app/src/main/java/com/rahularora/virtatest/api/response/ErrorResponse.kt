package com.rahularora.virtatest.api.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse (
    @SerializedName("status_code")
    var status_code: String,
    @SerializedName("message")
    var message: String
)
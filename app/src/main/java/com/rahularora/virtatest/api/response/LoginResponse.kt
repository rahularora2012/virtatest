package com.rahularora.virtatest.api.response

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("token")
    var token: String
)
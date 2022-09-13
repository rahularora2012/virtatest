package com.rahularora.virtatest.api.models

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("email")
    var email: String,
    @SerializedName("code")
    var password: String
)